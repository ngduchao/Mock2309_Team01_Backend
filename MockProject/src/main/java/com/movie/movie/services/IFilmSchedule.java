package com.movie.movie.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.movie.movie.models.FilmSchedule;

public interface IFilmSchedule {
    
    Page<FilmSchedule> getAllPage(Pageable pageable);

    List<FilmSchedule> getAll();

}
