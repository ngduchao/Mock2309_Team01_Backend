package com.vti.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vti.dto.UserUpdateDTO;
import com.vti.entity.User;

public interface IUserService extends UserDetailsService {

	void createUser(User user);

	User findUserByEmail(String email);

	User findByUserName(String userName);
	
	void activeUser(String token);

	void sendConfirmUserRegistrationViaEmail(String email);

	boolean existsUserByEmail(String email);

	boolean existsUserByUserName(String userName);
	
	boolean existsUserById(int id);

	void resetPasswordViaEmail(String email);

	void resetPassword(String token, String newPassword);

	void sendResetPasswordViaEmail(String email);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	void updateUser(String userName, UserUpdateDTO dto);
	
}
