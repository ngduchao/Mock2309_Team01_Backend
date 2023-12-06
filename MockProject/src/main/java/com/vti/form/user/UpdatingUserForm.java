package com.vti.form.user;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

import com.vti.validation.user.EmailNotExists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatingUserForm {
	
	@Length(max = 50, message = "The email's length is max 50 characters")
//	@Length(min = 6, message = "The email's length is min 6 characters")
	@Email
	@EmailNotExists
	private String email;

	private String firstName;
	
	private String lastName;
	
	
}
