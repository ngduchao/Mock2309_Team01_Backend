package com.vti.validation.film;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vti.service.IFilmService;


public class FilmNameNotExistsValidator implements ConstraintValidator<FilmNameNotExists, String>{

	@Autowired
	private IFilmService service;
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {

		if(StringUtils.isEmpty(name)) {
			return true;
		}
		
		return !service.isFilmExistsByName(name);
	}
	
	
}
