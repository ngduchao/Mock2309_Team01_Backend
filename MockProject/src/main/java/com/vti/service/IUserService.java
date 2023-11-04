package com.vti.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.User;
import com.vti.filter.UserFilterForm;
import com.vti.form.user.UpdatingUserForm;

public interface IUserService {

	public Page<User> getAllUsers(Pageable pageable, String search, UserFilterForm filter);
	
	public User getUserByID(Integer id);
	
	public void deleteUser(Integer id);
	
	public boolean isUserExistsByID(Integer id);
	
	public boolean isUserByUsername(String username);
	
	public boolean existsUserByEmail(String email);
	
	public void updateUser(Integer id, UpdatingUserForm form);
}
