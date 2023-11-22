package com.vti.form.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.vti.validation.user.EmailNotExists;
import com.vti.validation.user.UsernameNotExists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingUserByAdminForm {

	@NotBlank(message = "The username mustn't be null value")
	@Length(max = 50, message = "The username's length is max 50 characters")
	@Length(min = 6, message = "The username's length is min 6 characters")
	@UsernameNotExists
	private String username;
	
	@NotBlank(message = "The email mustn't be null value")
	@Length(max = 50, message = "The email's length is max 50 characters")
	@Length(min = 6, message = "The email's length is min 6 characters")
	@Email
	@EmailNotExists
	private String email;
	
	@NotBlank(message = "The password mustn't be null value")
	private String password;
	
	@NotBlank(message = "The first name mustn't be null value")
	private String firstName;
	
	@NotBlank(message = "The last name mustn't be null value")
	private String lastName;
	
	@Pattern(regexp = "Admin|Manager|User", message = "The role must be Admin, Manager or User")
	private String role;
}
