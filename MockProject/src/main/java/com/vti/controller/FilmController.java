package com.vti.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.FilmDTO;
import com.vti.entity.Film;
import com.vti.filter.FilmFilterForm;
import com.vti.service.IFilmService;
import com.vti.validation.film.FilmIDExists;

@RestController
@RequestMapping(value = "api/v1/films")
public class FilmController {
	@Autowired
	private IFilmService service;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping()
	public Page<FilmDTO> getAllFilms(Pageable pagable,
			@RequestParam(name = "search", required = false) String search,
			FilmFilterForm filter) {
		Page<Film> entitiePages = service.getAllFilms(pagable, search, filter);
		
		List<FilmDTO> dtos = modelMapper.map(entitiePages.getContent(), new TypeToken<List<FilmDTO>>() {}.getType());
		
		Page<FilmDTO> dtoPages = new PageImpl<>(dtos, pagable, entitiePages.getTotalElements());
		
		return dtoPages;
	}
	
	@GetMapping(value = "/{id}")
	public FilmDTO getFilmByID(@PathVariable(name = "id") @FilmIDExists Integer id) {
		
		Film entity = service.getFilmByID(id);
		
		FilmDTO dto = modelMapper.map(entity, FilmDTO.class);
	
		return dto;
	}
	
	@DeleteMapping(value = "{id}")
	public void deleteFilm(@FilmIDExists @PathVariable(name = "id") Integer id) {
		service.deleteFilm(id);
	}
	
//	@PostMapping()
//	public ResponseEntity<?> createFilm(@RequestBody CreatingFilmForm form){
//		service.createFilm(form);
//		return new ResponseEntity<String>("Create successfully!", HttpStatus.OK);
//	}
}
