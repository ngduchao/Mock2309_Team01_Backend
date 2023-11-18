package com.vti.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.Film;
import com.vti.entity.User;
import com.vti.filter.FilmFilterForm;
import com.vti.form.film.CreatingFilmForm;
import com.vti.form.film.UpdatingFilmForm;

public interface IFilmService {

	public Page<Film> getAllFilms(Pageable pageable, String search, FilmFilterForm filter);
	
	public Film getFilmByID(Integer id);
	
	public boolean isFilmExistsByName(String name);
	
	public boolean isFilmExistsByID(Integer id);
	
	public void deleteFilm(Integer id);
	
	public void createFilm(User user ,CreatingFilmForm form);
	
	public void updateFilm(Integer id, UpdatingFilmForm form);
}
