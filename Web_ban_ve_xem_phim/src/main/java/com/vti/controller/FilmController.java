package com.vti.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.FilmDTO;
import com.vti.entity.Film;
import com.vti.entity.User;
import com.vti.form.film.CreatingFilmForm;
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

	@Autowired
	private MessageSource messageSource;

	@GetMapping(value = "/lists")
	public List<FilmDTO> getListFilms() {
		List<Film> lst = service.getListFilms();

		// convert entities --> dtos
		List<FilmDTO> dtos = modelMapper.map(lst, new TypeToken<List<FilmDTO>>() {
		}.getType());

		return dtos;
	}

	@GetMapping(value = "/{id}")
	public FilmDTO getFilmByID(@PathVariable(name = "id") @FilmIDExists int id) {
		Film entity = service.getFilmByID(id);

		// convert entity to dto
		FilmDTO dto = modelMapper.map(entity, FilmDTO.class);

		dto.add(linkTo(methodOn(FilmController.class).getFilmByID(id)).withSelfRel());

		return dto;
	}

	@GetMapping(value = "filmName/{filmName}")
	public boolean isFilmExistsByName(@PathVariable(name = "filmName") String name) {
		
		return service.isFilmExistsByName(name);
		
	}

	@PostMapping()
	public ResponseEntity<?> createFilm(
			Authentication authentication
			, @RequestBody @Valid CreatingFilmForm form
			) {
		String username = authentication.getName();
		
		// get user info
		User authenUser = userService.findByUserName(username);
		
		service.createFilm(authenUser, form);
		
		return new ResponseEntity<>("Create sucesssfully!!!", HttpStatus.OK);
	}
	
	@GetMapping("/messages")
	public String getMessages(@RequestParam(value = "key") String key){
		return messageSource.getMessage(
				key, 
				null, 
				"Default message", 
				LocaleContextHolder.getLocale());
	}
	
	@GetMapping("/messages/vi")
	public String getMessagesVi(@RequestParam(value = "key") String key){
		return messageSource.getMessage(
				key, 
				null, 
				"Default message", 
				new Locale("vi", "VN"));
	}
	
	@GetMapping("/messages/en")
	public String getMessagesOther(@RequestParam(value = "key") String key){
		return messageSource.getMessage(
				key, 
				null, 
				"Default message", 
				Locale.US);
	}
}
