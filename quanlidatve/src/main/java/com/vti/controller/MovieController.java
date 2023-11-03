package com.vti.controller;

import com.vti.dto.MovieDto;
import com.vti.entity.Movie;
import com.vti.service.IMovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("api/v1/movies")
public class MovieController {
    @Autowired
    private IMovieService service;
    @Autowired
    private ModelMapper mapper;
    @GetMapping()
    public Page<MovieDto> findAll(Pageable pageable)
    {
        Page<Movie> movies =service.findAll(pageable);

        return movies.map(movie -> {
            return mapper.map(movie, MovieDto.class);
        });
    }
    public  MovieDto findById (@PathVariable("id") Integer id){
        Movie movies = service.findById(id);
        MovieDto dto =mapper.map(movies, MovieDto.class);
        return dto;
    }
    @PostMapping
    public void create(@RequestBody Movie movie){ service.create(movie);}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){

        service.deleteById(id);
        return  new ResponseEntity<String>("Delete Successfully!", HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteAllByIds(@RequestBody List<Integer> ids){

        service.deleteAllById(ids);
        return new ResponseEntity<String>("Delete Successfully!!",HttpStatus.OK);
    }

}
