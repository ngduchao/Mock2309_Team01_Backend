package com.vti.service;

import java.util.List;

import com.vti.entity.Film;
import com.vti.entity.User;
import com.vti.form.film.CreatingFilmForm;

public interface IFilmService {
	public List<Film> getListFilms();

	public Film getFilmByID(int filmId);

	public void createFilm(User authenUser, CreatingFilmForm form);

	public boolean isFilmExistsByName(String tenFilm);

	public boolean isFilmExistsByID(Integer id);
}
