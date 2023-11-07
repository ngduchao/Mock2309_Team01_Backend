package com.vti.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.UserDTO;
import com.vti.entity.User;
import com.vti.filter.UserFilterForm;
import com.vti.form.user.UpdatingUserForm;
import com.vti.service.IUserService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {
	
	@Autowired
	private IUserService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public ResponseEntity<?> getAllUsers(
			Pageable pageable,
			@RequestParam(value = "search", required = false) String search,
			UserFilterForm filter){
		
		Page<User> entityPages = service.getAllUsers(pageable, search, filter);
		
		List<UserDTO> dtos = modelMapper.map(entityPages.getContent(), new TypeToken<List<UserDTO>>() {
		}.getType());
			
		Page<UserDTO> dtoPage = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());
		
		return new ResponseEntity<>(dtoPage, HttpStatus.OK);
	}
			
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getUserByID(@PathVariable(name = "id") Integer id){
		
		User entity = service.getUserByID(id);
		
		UserDTO dto = modelMapper.map(entity, UserDTO.class);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "id/{id}")
	public ResponseEntity<?> existsByID(@PathVariable(name = "id") Integer id) {
		boolean result = service.isUserExistsByID(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "username/{username}")
	public ResponseEntity<?> existsByUsername(@PathVariable(name = "username") String username) {
		boolean result = service.isUserByUsername(username);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Integer id){
		service.deleteUser(id);
		return new ResponseEntity<>("Delete Successfully!", HttpStatus.OK);
	}
	
	@GetMapping(value = "/email/{email}")
	public ResponseEntity<?> existsUserByEmail(@PathVariable(name = "email") String email){
		boolean result = service.existsUserByEmail(email);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateUser(@PathVariable(name = "id") Integer id, @RequestBody UpdatingUserForm form){
		service.updateUser(id, form);
		
		return new ResponseEntity<>("Update Successfully!", HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO dto) {
		// create User
		service.createUser(dto.toEntity());

		return new ResponseEntity<>("We have sent an email. Please check email to active account!", HttpStatus.OK);
	}
	
	@GetMapping("/activeUser")
	// validate: check exists, check not expired
	public ResponseEntity<?> activeUserViaEmail(@RequestParam String token) {
		// active user
		service.activeUser(token);

		return new ResponseEntity<>("Active success!", HttpStatus.OK);
	}
	
	// resend confirm
	@GetMapping("/userRegistrationConfirmRequest")
	// validate: email exists, email not active
	public ResponseEntity<?> resendConfirmRegistrationViaEmail(@RequestParam String email) {

		service.sendConfirmUserRegistrationViaEmail(email);

		return new ResponseEntity<>("We have sent an email. Please check email to active account!", HttpStatus.OK);
	}
	
	// reset password confirm
	@GetMapping("/resetPasswordRequest")
	// validate: email exists, email not active
	public ResponseEntity<?> sendResetPasswordViaEmail(@RequestParam String email) {

		service.resetPasswordViaEmail(email);

		return new ResponseEntity<>("We have sent an email. Please check email to reset password!", HttpStatus.OK);
	}
	
	// resend reset password
	@GetMapping("/resendResetPassword")
	// validate: email exists, email not active
	public ResponseEntity<?> resendResetPasswordViaEmail(@RequestParam String email) {

		service.sendResetPasswordViaEmail(email);

		return new ResponseEntity<>("We have sent an email. Please check email to reset password!", HttpStatus.OK);
	}
	
	@GetMapping("/resetPassword")
	// validate: check exists, check not expired
	public ResponseEntity<?> resetPasswordViaEmail(@RequestParam String token, @RequestParam String newPassword) {

		// reset password
		service.resetPassword(token, newPassword);

		return new ResponseEntity<>("Reset Password success!", HttpStatus.OK);
	}
}
