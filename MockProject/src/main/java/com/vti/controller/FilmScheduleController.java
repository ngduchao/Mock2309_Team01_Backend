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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.FilmScheduleByFilmDTO;
import com.vti.dto.FilmScheduleDTO;
import com.vti.entity.Film;
import com.vti.entity.FilmSchedule;
import com.vti.form.filmSchedule.CreatingFilmSchedule;
import com.vti.form.filmSchedule.CreatingFilmScheduleForFilm;
import com.vti.form.filmSchedule.FilmScheduleFilterForm;
import com.vti.form.filmSchedule.UpdatingFilmScheduleForm;
import com.vti.service.IFilmScheduleService;
import com.vti.service.IFilmService;
import com.vti.validation.film.FilmIDExists;
import com.vti.validation.filmSchedule.FilmScheduleIDExists;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/film-schedules")
public class FilmScheduleController {
	@Autowired
    private IFilmScheduleService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IFilmService filmService;
	
	@PostMapping()
	public ResponseEntity<?> createFilmSchedule(@Valid @RequestBody CreatingFilmSchedule form){
		service.createFilmSchedule(form);
		return new ResponseEntity<String>("Create successfully!", HttpStatus.OK);
	}
	
	@PostMapping(value = "/create-for-film")
	public ResponseEntity<?> CreateFilmScheduleForFilm(@Valid @RequestBody CreatingFilmScheduleForFilm form) {
		Film film = filmService.getFilmByID(form.getFilmId());
		
    	service.CreateFilmScheduleForFilm(film, form);
		
    	return new ResponseEntity<String>("Create successfully!", HttpStatus.OK);
    }
	
	@GetMapping()
	public ResponseEntity<?> getAllFilmSchedules(
			@PageableDefault(sort = {"timeSlot"}, direction = Sort.Direction.ASC)
			Pageable pageable,
			 FilmScheduleFilterForm filter){
		Page<FilmSchedule> entity = service.getAllFilmSchedules(pageable, filter);
		
		List<FilmScheduleDTO> dtos = modelMapper.map(entity.getContent(), new TypeToken<List<FilmScheduleDTO>>() {}.getType());
	
		Page<FilmScheduleDTO> dtoPages = new PageImpl<>(dtos, pageable, entity.getTotalElements());
		
		return new ResponseEntity<>(dtoPages, HttpStatus.OK);
	}

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getFilmScheduleById(@FilmScheduleIDExists @PathVariable(name = "id") Integer id) {
    	FilmSchedule entity = service.getFilmScheduleByID(id);
		
		FilmScheduleDTO dto = modelMapper.map(entity, FilmScheduleDTO.class);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    
    @GetMapping(value = "/list/{filmId}")
    public ResponseEntity<?> getFilmScheduleByFilm(@PathVariable(name = "filmId") @FilmIDExists Integer filmId){
    	
    	List<FilmSchedule> filmSchedules = service.getFilmScheduleByFilm(filmId);
    	
		List<FilmScheduleByFilmDTO> dtos = modelMapper.map(filmSchedules, new TypeToken<List<FilmScheduleByFilmDTO>>() {}.getType());
    	
    	return new ResponseEntity<>(dtos, HttpStatus.OK);
    	
    }
    
    @PutMapping(value = "/{id}")
	public ResponseEntity<?> updateFilmSchedule(@FilmScheduleIDExists @PathVariable(name = "id") Integer scheduleId, @Valid @RequestBody UpdatingFilmScheduleForm form){
		service.updateFilmSchedule(scheduleId, form);
		return new ResponseEntity<>("Update Successfully!", HttpStatus.OK);
	}
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteFilmSchedule(@FilmScheduleIDExists @PathVariable(name = "id") Integer id){
    	
    	service.deleteFilmSchedule(id);
    	
    	return new ResponseEntity<>("Delete Successfully!", HttpStatus.OK);
    }

}