package com.vti.dto;

import com.vti.entity.Role;
import com.vti.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginInfoUser {

	private Integer id;

	private String token;

	private String username;

	private String email;

	private String firstName;

	private String lastName;

	private Role role;

	private String status;
	
	private String password;
	
	public LoginInfoUser(Integer id,String token, String userName, String email, String firstName, String lastName, Role role,
			String status, String password) {
		this.id = id;
		this.token = token;
		this.username = userName;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.status = status;
		this.password = password;
	}
}
