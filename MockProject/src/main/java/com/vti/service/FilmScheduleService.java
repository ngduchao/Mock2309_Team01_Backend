package com.vti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vti.entity.FilmSchedule;
import com.vti.form.filmschedule.UpdatingFilmScheduleForm;
import com.vti.repository.IFilmScheduleRepository;

@Service
public class FilmScheduleService implements IFilmScheduleService {

	@Autowired
	private IFilmScheduleRepository repository;

	@Override
	public Page<FilmSchedule> getAllFilmSchedules(Pageable pageable) {
		return repository.findAll(pageable);
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
}
