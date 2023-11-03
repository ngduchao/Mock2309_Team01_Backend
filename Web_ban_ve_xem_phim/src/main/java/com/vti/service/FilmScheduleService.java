package com.vti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.repository.IFilmScheduleRepository;

@Service
public class FilmScheduleService implements IFilmScheduleService {

	@Autowired
	private IFilmScheduleRepository repository;

	@Transactional
	public void deleteFilmSchedules(List<Short> ids) {
		repository.deleteByScheduleIdIn(ids);
		
	}
}