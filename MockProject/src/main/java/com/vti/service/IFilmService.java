package com.vti.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.dto.FilmDTO;
import com.vti.entity.Film;
import com.vti.filter.FilmFilterForm;
import com.vti.form.film.CreatingFilmForm;
import com.vti.form.film.UpdatingFilmForm;

public interface IFilmService {

	public Page<Film> getAllFilms(Pageable pageable, String search, FilmFilterForm filter);
	
	public Film getFilmByID(Integer id);
	
	public boolean isFilmExistsByName(String name);
	
	public boolean isFilmExistsByID(Integer id);
	
	public void deleteFilm(Integer id);
	
	public void createFilm(CreatingFilmForm form);
	
	public void updateFilm(UpdatingFilmForm form);
}
