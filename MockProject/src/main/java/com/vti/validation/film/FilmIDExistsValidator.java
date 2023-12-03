package com.vti.validation.film;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vti.service.IFilmService;

public class FilmIDExistsValidator implements ConstraintValidator<FilmIDExists, Integer>{
	
	@Autowired
	private IFilmService service;

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(Integer id, ConstraintValidatorContext context) {
		
		if(StringUtils.isEmpty(id)) {
			return true;
		}
		
		return service.isFilmExistsByID(id);
	}
	
	
}
