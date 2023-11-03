package com.vti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.service.IFilmScheduleService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/v1/filmschedules")
@Validated
public class FilmScheduleController {
	@Autowired
	private IFilmScheduleService service;
	
	@DeleteMapping(value = "/{ids}")
	public ResponseEntity<?> deleteGroups(@PathVariable(name = "ids") List<Short> ids) {
		service.deleteFilmSchedules(ids);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
	}
}