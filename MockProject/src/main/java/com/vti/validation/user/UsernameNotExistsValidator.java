package com.vti.validation.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vti.service.IUserService;

public class UsernameNotExistsValidator implements ConstraintValidator<UsernameNotExists, String>{

	@Autowired
	private IUserService service;

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {

		if (StringUtils.isEmpty(username)) {
			return true;
		}
		
		return !service.isUserExistsByUsername(username);
	}
	
	
}
