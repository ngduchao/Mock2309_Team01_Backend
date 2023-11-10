package com.vti.form.user;

import com.vti.entity.Role;
import com.vti.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingUserForm {
	
	private String username;
	
	private String email;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private Role role;
	
	public User toEntity() {
		return new User(username, email, password, firstName, lastName, role);
	}
}
