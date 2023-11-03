package com.vti.service;

import com.vti.entity.Movie;
import com.vti.repository.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieService implements IMovieService {
    @Autowired
    private IMovieRepository repository;
    @Override
    public Page<Movie> findAll(Pageable pageable) {

        return repository.findAll(pageable);
    }
    @Override
    public Movie findById(Integer id){
        return repository.findById(id).orElse(null);
    }
    @Override
    public void create(Movie movie){
        repository.save(movie);
    }
    @Override
    public void deleteById(Integer id){
        repository.deleteById(id);
    }
    @Transactional
    @Override
    public void deleteAllById(List<Integer> ids){repository.deleteAllById(ids);}
}
