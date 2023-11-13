package com.movie.movie.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.movie.movie.models.FilmSchedule;
import com.movie.movie.repositories.FilmScheduleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilmScheduleService implements IFilmSchedule{
    private final FilmScheduleRepository filmScheduleRepository;

    @Override
    public Page<FilmSchedule> getAllPage(Pageable pageable) {
        return filmScheduleRepository.findAll(pageable);
    }

    @Override
    public List<FilmSchedule> getAll() {
        return filmScheduleRepository.findAll();
    }
    
}
