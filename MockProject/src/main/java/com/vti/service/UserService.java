package com.vti.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.entity.RegistrationUserToken;
import com.vti.entity.ResetPasswordToken;
import com.vti.entity.Role;
import com.vti.entity.User;
import com.vti.entity.UserStatus;
import com.vti.event.OnResetPasswordViaEmailEvent;
import com.vti.event.OnSendRegistrationUserConfirmViaEmailEvent;
import com.vti.filter.UserFilterForm;
import com.vti.form.user.CreatingUserByAdminForm;
import com.vti.form.user.UpdatingUserForm;
import com.vti.repository.IUserRepository;
import com.vti.repository.RegistrationUserTokenRepository;
import com.vti.repository.ResetPasswordTokenRepository;
import com.vti.specification.user.UserSpecification;

@Service
public class UserService implements IUserService,UserDetailsService{
	
	@Autowired 
	private IUserRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RegistrationUserTokenRepository registrationUserTokenRepository;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@Autowired
	private ResetPasswordTokenRepository resetPasswordTokenRepository;

	@Override
	public Page<User> getAllUsers(Pageable pageable, String search, UserFilterForm filter) {

		Specification<User> where = UserSpecification.buildWhere(search, filter);
		
		return repository.findAll(where ,pageable);
	}

	@Override
	public User getUserByID(Integer id) {
		return repository.getById(id);
	}

	@Override
	public boolean isUserExistsByID(Integer id) {
		return repository.existsById(id);
	}

	@Override
	public boolean isUserExistsByUsername(String username) {
		return repository.existsByUsername(username);
	}

//	@Override
//	public void deleteUser(Integer id) {
//		repository.deleteById(id);
//	}

	@Override
	public boolean existsUserByEmail(String email) {
		return repository.existsByEmail(email);
	}

	@Override
	public void updateUser(Integer id, UpdatingUserForm form) {
		
		User entity = repository.findById(id).get();
		
		if(form.getEmail() == null || form.getEmail().isEmpty()) {
			form.setEmail(entity.getEmail());
		}
		if(form.getFirstName() == null || form.getFirstName().isEmpty()) {
			form.setFirstName(entity.getFirstName());
		}
		if(form.getLastName() == null || form.getLastName().isEmpty()) {
			form.setLastName(entity.getLastName());
		}
		
		entity.setEmail(form.getEmail());
		entity.setFirstName(form.getFirstName());
		entity.setLastName(form.getLastName());
		
		repository.save(entity);
	}
	
	@Override
	public User findUserByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public User findUserByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Check user exists by username
		User user = repository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),authorities);
	}
	


	@Override
	public void Register(User user) {
		// encode password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		user.setRole(Role.User);

		// create user
		repository.save(user);

		// create new user registration token
		createNewRegistrationUserToken(user);

		// send email to confirm
		sendConfirmUserRegistrationViaEmail(user.getEmail());
	}
	
	@Override
	public void createUserByAdmin(CreatingUserByAdminForm form) {
		
		User user = modelMapper.map(form, User.class);
		
		// encode password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		user.setStatus(UserStatus.ACTIVE);
		
		// create user
		repository.save(user);
	}
	
	private void createNewRegistrationUserToken(User user) {

		// create new token for confirm Registration
		final String newToken = UUID.randomUUID().toString();
		RegistrationUserToken token = new RegistrationUserToken(newToken, user);

		registrationUserTokenRepository.save(token);
	}

	@Override
	public void activeUser(String token) {
		// get token
		RegistrationUserToken registrationUserToken = registrationUserTokenRepository.findByToken(token);

		// active user
		User user = registrationUserToken.getUser();
		user.setStatus(UserStatus.ACTIVE);
		repository.save(user);

		// remove Registration User Token
		registrationUserTokenRepository.deleteById(registrationUserToken.getId());
	}

	@Override
	public void sendConfirmUserRegistrationViaEmail(String email) {
		eventPublisher.publishEvent(new OnSendRegistrationUserConfirmViaEmailEvent(email));
	}

	@Override
	public boolean existsUserByUsername(String userName) {
		return repository.existsByUsername(userName);
	}
	
	@Override
	public void sendResetPasswordViaEmail(String email) {
		eventPublisher.publishEvent(new OnResetPasswordViaEmailEvent(email));
	}

	@Override
	public void resetPasswordViaEmail(String email) {
		// find user by email
		User user = findUserByEmail(email);

		// remove token token if exists
		resetPasswordTokenRepository.deleteByUserId(user.getId());

		// create new reset password token
		createNewResetPasswordToken(user);

		// send email
		sendResetPasswordViaEmail(email);
		
	}
	
	private void createNewResetPasswordToken(User user) {

		// create new token for Reseting password
		final String newToken = UUID.randomUUID().toString();
		ResetPasswordToken token = new ResetPasswordToken(newToken, user);

		resetPasswordTokenRepository.save(token);
	}

	@Override
	public void resetPassword(String token, String newPassword) {
		
		// get token
		ResetPasswordToken resetPasswordToken = resetPasswordTokenRepository.findByToken(token);

		// change password
		User user = resetPasswordToken.getUser();
		user.setPassword(passwordEncoder.encode(newPassword));
		repository.save(user);

		// remove Reset Password
		resetPasswordTokenRepository.deleteById(resetPasswordToken.getId());
	}

	@Transactional
	public void deleteUsers(List<Integer> ids) {
		repository.deleteByIdIn(ids);
	}



//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	
//	User user = repository.findByUsername(username);
//	if (user!=null) {
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
//			return  new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),authorities);
//		}else {
//			throw new UsernameNotFoundException(username);
//		}
//	}
	
}
