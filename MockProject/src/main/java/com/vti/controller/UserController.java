package com.vti.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.ProfileDTO;
import com.vti.dto.UserDTO;
import com.vti.entity.User;
import com.vti.filter.UserFilterForm;
import com.vti.form.user.CreatingUserByAdminForm;
import com.vti.form.user.CreatingUserForm;
import com.vti.form.user.UpdatingUserForm;
import com.vti.service.IUserService;
import com.vti.validation.user.UserIDExists;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/users")
@Validated
public class UserController {
	
	@Autowired
	private IUserService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public ResponseEntity<?> getAllUsers(
			@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) 
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
	public ResponseEntity<?> getUserByID(@PathVariable(name = "id") @UserIDExists Integer id){
		
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
		boolean result = service.isUserExistsByUsername(username);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{ids}")
	public ResponseEntity<?> deleteUsers(@PathVariable(name = "ids") List<Integer> ids){
		service.deleteUsers(ids);
		return new ResponseEntity<>("Delete Successfully!", HttpStatus.OK);
	}
	
	@GetMapping(value = "/email/{email}")
	public ResponseEntity<?> existsUserByEmail(@PathVariable(name = "email") String email){
		boolean result = service.existsUserByEmail(email);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateUser(@PathVariable(name = "id") @UserIDExists Integer id, @Valid @RequestBody UpdatingUserForm form){
		service.updateUser(id, form);
		
		return new ResponseEntity<>("Update Successfully!", HttpStatus.OK);
	}
	
	//người dùng tự đăng ký
	@PostMapping()
	public ResponseEntity<?> Register(@Valid @RequestBody CreatingUserForm dto) {
		// create User
		service.Register(dto.toEntity());

		return new ResponseEntity<>("We have sent an email. Please check email to active account!", HttpStatus.OK);
	}
	
	@PostMapping(value = "/byAdmin")
	public ResponseEntity<?> createUserByAdmin(@Valid @RequestBody CreatingUserByAdminForm form){
		
		service.createUserByAdmin(form);
		
		return new ResponseEntity<>("Create Successfully!", HttpStatus.OK);
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
	
	@GetMapping(value = "/profile")
	public ResponseEntity<?> getUserProfile(Authentication authentication){
		
		String username = authentication.getName();
		
		User user = service.findUserByUsername(username);
		
		ProfileDTO profileDTO = new ProfileDTO(
				user.getUsername(),
				user.getEmail(),
				user.getFirstName(),
				user.getLastName(),
				user.getRole().name(),
				user.getStatus().toString());
		return new ResponseEntity<>(profileDTO, HttpStatus.OK);
	}
}
