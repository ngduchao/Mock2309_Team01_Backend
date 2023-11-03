package com.vti.dto;

import com.vti.entity.UserRole;
import com.vti.entity.UserStatus;

public class ProfileDTO {
	private int userId;
	
	private String userName;

	private String email;

	private String firstName;

	private String lastName;

	private UserRole role;

	private UserStatus status;

	public ProfileDTO(int userId, String userName, String email, String firstName, String lastName, UserRole role, UserStatus status) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public UserRole getRole() {
		return role;
	}

	public UserStatus getStatus() {
		return status;
	}
	public int getUserId() {
		return userId;
	}
}
