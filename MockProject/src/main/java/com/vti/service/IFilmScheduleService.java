package com.vti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.Film;
import com.vti.entity.FilmSchedule;
//import com.vti.form.filmschedule.UpdatingFilmScheduleForm;
import com.vti.form.filmSchedule.CreatingFilmSchedule;
import com.vti.form.filmSchedule.CreatingFilmScheduleForFilm;
import com.vti.form.filmSchedule.FilmScheduleFilterForm;
import com.vti.form.filmSchedule.UpdatingFilmScheduleForm;

public interface IFilmScheduleService {
    
	public Page<FilmSchedule> getAllFilmSchedules(Pageable pageable, FilmScheduleFilterForm filter);

	public FilmSchedule getFilmScheduleByID(Integer id);
	
	public void updateFilmSchedule(Integer id, UpdatingFilmScheduleForm form);
	
	public void createFilmSchedule (CreatingFilmSchedule form);

	public boolean isFilmScheduleExistsByID(Integer id);
	
	public void deleteFilmSchedule(Integer id);
	
	public List<FilmSchedule> getFilmScheduleByFilm(Integer filmId);
	
	public void CreateFilmScheduleForFilm (Film film ,CreatingFilmScheduleForFilm form);
}