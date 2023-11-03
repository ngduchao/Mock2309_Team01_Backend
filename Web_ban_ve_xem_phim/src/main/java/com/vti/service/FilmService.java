package com.vti.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.entity.Film;
import com.vti.entity.User;
import com.vti.form.film.CreatingFilmForm;
import com.vti.repository.IFilmRepository;

@Service
public class FilmService implements IFilmService {
	
	@Autowired
	private IFilmRepository repository;
	
	public List<Film> getListFilms() {
		return repository.getListFilm();
	}

	public Film getFilmByID(int filmId) {
		return repository.findById(filmId).get();
	}

	@Transactional
	public void createFilm(User authenUser, CreatingFilmForm form) {
		
		// convert form to entity
		Film filmEntity = form.toEntity();
		
		filmEntity.setUser(authenUser);
		
		// create department
		repository.save(filmEntity);
	}
	
	public boolean isFilmExistsByName(String name) {
		return repository.existsByName(name);
	}

	public boolean isFilmExistsByID(Integer filmId) {
		return repository.existsById(filmId);
	}
}
