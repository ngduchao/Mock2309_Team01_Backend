package com.vti.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vti.entity.Film;
import com.vti.filter.FilmFilterForm;
import com.vti.form.film.CreatingFilmForm;
import com.vti.form.film.UpdatingFilmForm;
import com.vti.repository.IFilmRepository;
import com.vti.specification.film.FilmSpecification;

@Service
public class FilmService implements IFilmService {
	
	@Autowired 
	private IFilmRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Page<Film> getAllFilms(Pageable pageable, String search, FilmFilterForm filter) {
		
		Specification<Film> where = FilmSpecification.buildWhere(search, filter);
		
		return repository.findAll(where, pageable);
	}

	@Override
	public boolean isFilmExistsByName(String name) {
		return repository.existsByName(name);
	}
	
	public boolean isFilmExistsByID(Integer id) {
		return repository.existsById(id);
	}

	@Override
	public Film getFilmByID(Integer id) {
		return repository.findById(id).get();
	}

	@Override
	public void deleteFilm(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public void createFilm(CreatingFilmForm form) {
		repository.save(form.toEntity());
	}

	@Override
	public void updateFilm(UpdatingFilmForm form) {
		// TODO Auto-generated method stub
		
	}
	
}

