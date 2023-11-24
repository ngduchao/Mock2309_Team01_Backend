package com.vti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vti.entity.Film;
import com.vti.entity.FilmSchedule;
import com.vti.form.filmSchedule.CreatingFilmSchedule;
import com.vti.form.filmSchedule.CreatingFilmScheduleForFilm;
import com.vti.form.filmSchedule.FilmScheduleFilterForm;
import com.vti.form.filmSchedule.UpdatingFilmScheduleForm;
import com.vti.repository.IFilmRepository;
//import com.vti.form.filmschedule.UpdatingFilmScheduleForm;
import com.vti.repository.IFilmScheduleRepository;
import com.vti.specification.filmSchedule.FilmScheduleSpecification;

@Service
public class FilmScheduleService implements IFilmScheduleService {

	@Autowired
	private IFilmScheduleRepository repository;
	
	@Autowired
	private IFilmRepository filmRepository;

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
		
		if(form.getSeatNumber() == null) {
			form.setSeatNumber(entity.getSeatNumber());
		}
		if(form.getTimeSlot() == null) {
			form.setTimeSlot(entity.getTimeSlot());
		}
		
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

	@Override
	public List<FilmSchedule> getFilmScheduleByFilm(Integer filmId) {

		Film film = filmRepository.getById(filmId);
		
		List<FilmSchedule> filmSchedules = film.getFilmSchedules();
		
		return filmSchedules;
	}

	@Override
	public void CreateFilmScheduleForFilm(Film film, CreatingFilmScheduleForFilm form) {
		FilmSchedule filmSchedule = form.toEntity();
		
		filmSchedule.setFilm(film);
		
		repository.save(filmSchedule);
	}
}