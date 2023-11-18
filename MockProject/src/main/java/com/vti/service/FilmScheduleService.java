package com.vti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vti.entity.Film;
import com.vti.entity.FilmSchedule;
import com.vti.form.filmSchedule.CreatingFilmSchedule;
import com.vti.form.filmSchedule.FilmScheduleFilterForm;
import com.vti.form.filmSchedule.UpdatingFilmScheduleForm;
//import com.vti.form.filmschedule.UpdatingFilmScheduleForm;
import com.vti.repository.IFilmScheduleRepository;
import com.vti.specification.filmSchedule.FilmScheduleSpecification;

@Service
public class FilmScheduleService implements IFilmScheduleService {

	@Autowired
	private IFilmScheduleRepository repository;

	@Override
	public Page<FilmSchedule> getAllFilmSchedules(Pageable pageable, FilmScheduleFilterForm filter) {
		
		Specification<FilmSchedule> where = FilmScheduleSpecification.buildWhere(filter);
		
		return repository.findAll(where, pageable);
	}

	@Override
	public FilmSchedule getFilmScheduleByID(Integer id) {
		return repository.findById(id).get();
	}

	@Override
	public void updateFilmSchedule(Integer scheduleId, UpdatingFilmScheduleForm form) {
		FilmSchedule entity = repository.getById(scheduleId);
		entity.setSeatNumber(form.getSeatNumber());
		entity.setTimeSlot(form.getTimeSlot());
		repository.save(entity);
	}

	@Override
	public void createFilmSchedule(CreatingFilmSchedule form) {
		FilmSchedule filmSchedule = form.toEntity();
		repository.save(filmSchedule);
	}
	
	public boolean isFilmScheduleExistsByID(Integer id) {
		return repository.existsById(id);
	}

	@Override
	public void deleteFilmSchedule(Integer id) {
		repository.deleteById(id);
	}

//	@Override
//	public void CreateFilmScheduleForFilm(Film film, CreatingFilmSchedule form) {
//		FilmSchedule filmSchedule = form.toEntity();
//		
//		filmSchedule.setFilm(film);
//		
//		repository.save(filmSchedule);
//	}
}