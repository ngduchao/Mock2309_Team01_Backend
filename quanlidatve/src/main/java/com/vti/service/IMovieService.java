package com.vti.service;

import com.vti.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface IMovieService {

    Page<Movie> findAll(Pageable pageable);
    Movie findById (Integer id);
    void create (Movie movie);
    void deleteById(Integer id);
    void deleteAllById(List<Integer> ids);
}
