package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vti.entity.Film;
import com.vti.entity.FilmSchedule;

@Repository
public interface IFilmScheduleRepository extends JpaRepository<FilmSchedule, Integer>,JpaSpecificationExecutor<FilmSchedule> {
	
}