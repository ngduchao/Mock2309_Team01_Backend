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
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.FilmDTO;
import com.vti.entity.Film;
import com.vti.entity.User;
import com.vti.filter.FilmFilterForm;
import com.vti.form.film.CreatingFilmForm;
import com.vti.form.film.UpdatingFilmForm;
import com.vti.service.IFilmService;
import com.vti.service.IUserService;
import com.vti.validation.film.FilmIDExists;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/v1/films")
@Validated
public class FilmController {
	@Autowired
	private IFilmService service;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public ResponseEntity<?> getAllFilms(
			@PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC)
			Pageable pagable,
			@RequestParam(name = "search", required = false) String search,
			FilmFilterForm filter){
		
		Page<Film> entitiePages = service.getAllFilms(pagable, search, filter);
		
		List<FilmDTO> dtos = modelMapper.map(entitiePages.getContent(), new TypeToken<List<FilmDTO>>() {}.getType());
		
		Page<FilmDTO> dtoPages = new PageImpl<>(dtos, pagable, entitiePages.getTotalElements());
		
		return new ResponseEntity<>(dtoPages, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getFilmByID(@PathVariable(name = "id") @FilmIDExists Integer id){
		
		Film entity = service.getFilmByID(id);
		
		FilmDTO dto = modelMapper.map(entity, FilmDTO.class);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<?> deleteFilm(@FilmIDExists @PathVariable(name = "id") Integer id){
		
		service.deleteFilm(id);
		
		return new ResponseEntity<>("Delete Successfully!", HttpStatus.OK);
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<?> updateFilm(@PathVariable(name = "id") @FilmIDExists Integer id,@Valid @RequestBody UpdatingFilmForm form){
		
		service.updateFilm(id, form);
		return new ResponseEntity<>("Update Successfully!", HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> createFilm(@Valid @RequestBody CreatingFilmForm form, Authentication authentication){
		
		String username = authentication.getName();
		
		User userInfo = userService.findUserByUsername(username);
		service.createFilm(userInfo, form);
		
		return new ResponseEntity<String>("Create successfully!", HttpStatus.OK);
	}
}
