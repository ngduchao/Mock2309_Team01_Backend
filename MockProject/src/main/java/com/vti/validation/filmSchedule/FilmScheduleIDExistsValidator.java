package com.vti.validation.filmSchedule;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vti.service.IFilmScheduleService;


public class FilmScheduleIDExistsValidator implements ConstraintValidator<FilmScheduleIDExists, Integer>{
	
	@Autowired
	private IFilmScheduleService service;
	
	public boolean isValid(Integer id, ConstraintValidatorContext context) {
		
		if(StringUtils.isEmpty(id)) {
			return true;
		}
		
		return service.isFilmScheduleExistsByID(id);
	}
}
