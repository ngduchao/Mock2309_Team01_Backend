package com.vti.dto;

public class ProfileDTO {

	private String username;

	private String email;

	private String firstName;

	private String lastName;

	private String role;

	private String status;

	public ProfileDTO(String userName, String email, String firstName, String lastName, String role, String status) {
		this.username = userName;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.status = status;
	}

	public String getUsername() {
		return username;
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

	public String getRole() {
		return role;
	}

	public String getStatus() {
		return status;
	}
}
