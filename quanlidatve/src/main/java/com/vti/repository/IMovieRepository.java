package com.vti.repository;

import com.vti.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IMovieRepository extends JpaRepository<Movie,Integer>{
}
