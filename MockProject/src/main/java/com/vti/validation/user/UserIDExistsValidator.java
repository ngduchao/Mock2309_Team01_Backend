package com.vti.validation.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vti.service.IUserService;


public class UserIDExistsValidator implements ConstraintValidator<UserIDExists, Integer>{
	
	@Autowired
	private IUserService userService;

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(Integer id, ConstraintValidatorContext context) {
		
		if(StringUtils.isEmpty(id)) {
			return true;
		}
		
		return userService.isUserExistsByID(id);
	}

}
