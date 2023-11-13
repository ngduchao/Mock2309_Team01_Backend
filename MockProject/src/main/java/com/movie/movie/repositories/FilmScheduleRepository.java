package com.movie.movie.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.movie.models.FilmSchedule;

@Repository
public interface FilmScheduleRepository extends JpaRepository<FilmSchedule, Integer> {

    Page<FilmSchedule> findAll(Pageable pageable);

}
