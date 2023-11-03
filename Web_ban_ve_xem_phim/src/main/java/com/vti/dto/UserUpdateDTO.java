package com.vti.dto;

public class UserUpdateDTO {
	// check not null, check length, check format (regex)...
	private String firstName;

	// check not null, check length, check format (regex)...
	private String lastName;

	public UserUpdateDTO() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}