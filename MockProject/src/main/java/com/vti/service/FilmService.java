package com.vti.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vti.entity.Film;
import com.vti.entity.User;
import com.vti.filter.FilmFilterForm;
import com.vti.form.film.CreatingFilmForm;
import com.vti.form.film.UpdatingFilmForm;
import com.vti.repository.IFilmRepository;
import com.vti.specification.film.FilmSpecification;

@Service
public class FilmService implements IFilmService {
	
	@Autowired 
	private IFilmRepository repository;

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
	@Transactional
	public void createFilm(User user, CreatingFilmForm form) {
		Film film = form.toEntity();
		
		film.setUser(user);
		
		repository.save(film);
	}

	public void updateFilm(Integer id, UpdatingFilmForm form) {
		Film entity = repository.getById(id);
		
		entity.setName(form.getName());
		entity.setDirectors(form.getDirectors());
		entity.setActors(form.getActors());
		entity.setGenre(form.getGenre());
		entity.setDuration(form.getDuration());
		entity.setDescription(form.getDescription());
		entity.setTicketPrice(form.getTicketPrice());
		entity.setPoster(form.getPoster());
		
		repository.save(entity);
	}
//
//	@Override
//	@Transactional
//	public void deleteFilms(List<Integer> ids) {
//		repository.deleteByIdIn(ids);
//	}
	
}

