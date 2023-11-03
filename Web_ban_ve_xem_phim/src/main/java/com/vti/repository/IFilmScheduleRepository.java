package com.vti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vti.entity.FilmSchedule;

public interface IFilmScheduleRepository extends JpaRepository<FilmSchedule, Integer>, JpaSpecificationExecutor<FilmSchedule> {

	public void deleteByScheduleIdIn(List<Short> ids);
}