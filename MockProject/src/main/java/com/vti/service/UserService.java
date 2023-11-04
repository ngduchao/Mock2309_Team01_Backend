package com.vti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vti.entity.User;
import com.vti.filter.UserFilterForm;
import com.vti.form.user.UpdatingUserForm;
import com.vti.repository.IUserRepository;
import com.vti.specification.user.UserSpecification;

@Service
public class UserService implements IUserService{
	
	@Autowired 
	private IUserRepository repository;

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
	public boolean isUserByUsername(String username) {
		return repository.existsByUsername(username);
	}

	@Override
	public void deleteUser(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public boolean existsUserByEmail(String email) {
		return repository.existsByEmail(email);
	}

	@Override
	public void updateUser(Integer id, UpdatingUserForm form) {
		
		User entity = repository.findById(id).get();
		
		entity.setEmail(form.getEmail());
		entity.setFirstName(form.getFirstName());
		entity.setLastName(form.getLastName());
		
		repository.save(entity);
	}
}
