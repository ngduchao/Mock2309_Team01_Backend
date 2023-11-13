package com.movie.movie.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.movie.models.FilmSchedule;
import com.movie.movie.services.FilmScheduleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/film-schedules")
@Validated
@RequiredArgsConstructor

// http://localhost:8080/api/v1/film-schedules?page=0&size=5
public class FilmScheduleController {
    private final FilmScheduleService filmScheduleService;

    @GetMapping
    public Page<FilmSchedule> getAllPage(Pageable pageable) {
        return filmScheduleService.getAllPage(pageable);
    }

    @GetMapping("/all")
    public List<FilmSchedule> getAll() {
        return filmScheduleService.getAll();
    }
}