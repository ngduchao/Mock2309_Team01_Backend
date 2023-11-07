package com.vti.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginInfoUser {

	private Integer id;

	private String token;

	private String username;

	private String email;

	private String firstName;

	private String lastName;

	private String role;

	private String status;
	
	private String password;
	
	public LoginInfoUser(Integer id,String token, String userName, String email, String firstName, String lastName, String role,
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
