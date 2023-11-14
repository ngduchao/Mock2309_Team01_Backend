package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.vti.entity.Film;

@Repository
public interface IFilmRepository extends JpaRepository<Film, Integer>, JpaSpecificationExecutor<Film> {
	
	boolean existsByName(String name);
	
	Film findByName(String name);
}
