package com.vti.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.ProfileDTO;
import com.vti.dto.UserDTO;
import com.vti.dto.UserUpdateDTO;
import com.vti.entity.User;
import com.vti.service.IUserService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/users")
@Validated
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping(value = "/email/{email}")
	public ResponseEntity<?> existsUserByEmail(@PathVariable(name = "email") String email) {
		// get entity
		boolean result = userService.existsUserByEmail(email);

		// return result
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/userName/{userName}")
	public ResponseEntity<?> existsUserByUserName(@PathVariable(name = "userName") String userName) {
		// get entity
		boolean result = userService.existsUserByUserName(userName);

		// return result
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<?> existsUserById(@PathVariable(name = "id") int id) {
		// get entity
		boolean result = userService.existsUserById(id);

		// return result
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO dto) {
		// create User
		userService.createUser(dto.toEntity());

		return new ResponseEntity<>("We have sent an email. Please check email to active account!", HttpStatus.OK);
	}

	@GetMapping("/activeUser")
	// validate: check exists, check not expired
	public ResponseEntity<?> activeUserViaEmail(@RequestParam String token) {
		// active user
		userService.activeUser(token);

		return new ResponseEntity<>("Active success!", HttpStatus.OK);
	}

	// resend confirm
	@GetMapping("/userRegistrationConfirmRequest")
	// validate: email exists, email not active
	public ResponseEntity<?> resendConfirmRegistrationViaEmail(@RequestParam String email) {

		userService.sendConfirmUserRegistrationViaEmail(email);

		return new ResponseEntity<>("We have sent an email. Please check email to active account!", HttpStatus.OK);
	}

	// reset password confirm
	@GetMapping("/resetPasswordRequest")
	// validate: email exists, email not active
	public ResponseEntity<?> sendResetPasswordViaEmail(@RequestParam String email) {

		userService.resetPasswordViaEmail(email);

		return new ResponseEntity<>("We have sent an email. Please check email to reset password!", HttpStatus.OK);
	}

	// resend reset password
	@GetMapping("/resendResetPassword")
	// validate: email exists, email not active
	public ResponseEntity<?> resendResetPasswordViaEmail(@RequestParam String email) {

		userService.sendResetPasswordViaEmail(email);

		return new ResponseEntity<>("We have sent an email. Please check email to reset password!", HttpStatus.OK);
	}

	@GetMapping("/resetPassword")
	// validate: check exists, check not expired
	public ResponseEntity<?> resetPasswordViaEmail(@RequestParam String token, @RequestParam String newPassword) {

		// reset password
		userService.resetPassword(token, newPassword);

		return new ResponseEntity<>("Reset Password success!", HttpStatus.OK);
	}

	@GetMapping("/profile")
	// validate: check exists, check not expired
	public ResponseEntity<?> getUserProfile(Authentication authentication) {
		
		// get username from token
		String username = authentication.getName();
		
		// get user info
		User user = userService.findByUserName(username);
		
        // convert user entity to user dto
		ProfileDTO profileDto = new ProfileDTO(
				user.getUserId(),
        		user.getUserName(), 
        		user.getEmail(), 
        		user.getFirstName(), 
        		user.getLastName(),
        		user.getRole(),
        		user.getStatus()
        );

		return new ResponseEntity<>(profileDto, HttpStatus.OK);
	}
	
	@PutMapping("/profileName")
	// validate: check exists, check not expired
	public ResponseEntity<?> updateUser(Authentication authentication, @RequestBody UserUpdateDTO dto) {
		
		// get username from token
		String username = authentication.getName();
		
		userService.updateUser(username, dto);
		
		return new ResponseEntity<>("Update user Successfully!", HttpStatus.OK);
	}
}
