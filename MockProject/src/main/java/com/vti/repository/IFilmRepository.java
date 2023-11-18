package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vti.entity.Film;

public interface IFilmRepository extends JpaRepository<Film, Integer>, JpaSpecificationExecutor<Film> {
	
	boolean existsByName(String name);
	
	Film findByName(String name);
}
