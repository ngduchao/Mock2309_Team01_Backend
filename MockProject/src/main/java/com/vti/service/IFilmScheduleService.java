package com.vti.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.FilmSchedule;
import com.vti.form.filmschedule.UpdatingFilmScheduleForm;

public interface IFilmScheduleService {
    
	public Page<FilmSchedule> getAllFilmSchedules(Pageable pageable);

	public FilmSchedule getFilmScheduleByID(Integer id);
	
	public void updateFilmSchedule(Integer id, UpdatingFilmScheduleForm form);
}