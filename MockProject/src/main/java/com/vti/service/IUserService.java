package com.vti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vti.entity.User;
import com.vti.filter.UserFilterForm;
import com.vti.form.user.CreatingUserByAdminForm;
import com.vti.form.user.UpdatingUserForm;

public interface IUserService extends UserDetailsService{
	
	public void Register(User user);
	
	public void createUserByAdmin(CreatingUserByAdminForm form);

	public Page<User> getAllUsers(Pageable pageable, String search, UserFilterForm filter);
	
	public User getUserByID(Integer id);
	
//	public void deleteUser(Integer id);
	
	public boolean isUserExistsByID(Integer id);
	
	public boolean isUserExistsByUsername(String username);
	
	public boolean existsUserByEmail(String email);
	
	public void updateUser(Integer id, UpdatingUserForm form);
	
	public User findUserByUsername(String username);
	
	public User findUserByEmail(String email);
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	public void activeUser(String token);
	
	public void sendConfirmUserRegistrationViaEmail(String email);
	
	public boolean existsUserByUsername(String userName);
	
	public void resetPasswordViaEmail(String email);
	
	public void resetPassword(String token, String newPassword);
	
	public void sendResetPasswordViaEmail(String email);
	
	public void deleteUsers(List<Integer> ids);
}
