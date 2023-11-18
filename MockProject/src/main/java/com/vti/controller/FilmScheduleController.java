package com.vti.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.FilmScheduleDTO;
import com.vti.entity.FilmSchedule;
import com.vti.form.filmschedule.UpdatingFilmScheduleForm;
import com.vti.service.IFilmScheduleService;

@RestController
@RequestMapping(value = "/api/v1/film-schedules")
public class FilmScheduleController {
	@Autowired
    private IFilmScheduleService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public ResponseEntity<?> getAllFilms(
			@PageableDefault(sort = {"timeSlot"}, direction = Sort.Direction.ASC)
			Pageable pagable){
		
		Page<FilmSchedule> entitiePages = service.getAllFilmSchedules(pagable);
		
		List<FilmScheduleDTO> dtos = modelMapper.map(entitiePages.getContent(), new TypeToken<List<FilmScheduleDTO>>() {}.getType());
		
		Page<FilmScheduleDTO> dtoPages = new PageImpl<>(dtos, pagable, entitiePages.getTotalElements());
		
		return new ResponseEntity<>(dtoPages, HttpStatus.OK);
	}
	
//	@PostMapping(value = "/{id}")
//	public ResponseEntity<?> CreateFilmSchedule(@PathVariable(name = "id") Integer filmId, CreatingFilmScheduleForm form) {
//		service.createFilmSchedule(1, form);
//		
//		return new ResponseEntity<>("Create successfully!!", HttpStatus.OK);
//    }
	
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getFilmScheduleById(@PathVariable(name = "id") Integer id) {
    	FilmSchedule entity = service.getFilmScheduleByID(id);
		
		FilmScheduleDTO dto = modelMapper.map(entity, FilmScheduleDTO.class);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    
    @PutMapping(value = "/{id}")
	public ResponseEntity<?> updateFilmSchedule(@PathVariable(name = "id") Integer scheduleId, @RequestBody UpdatingFilmScheduleForm form){
		service.updateFilmSchedule(scheduleId, form);
		return new ResponseEntity<>("Update Successfully!", HttpStatus.OK);
	}
}
