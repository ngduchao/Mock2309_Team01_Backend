package com.vti.dto;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.vti.entity.Film;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilmDTO extends RepresentationModel<FilmDTO> {
	private int filmId;
	
	private String name;

	private String directors;
	
	private String actors;
	
	private String genre;
	
	private String duration;
	
	private String description;
	
	private Integer ticketPrice;

	private Date releaseDate;
	
	private String poster;
	
	private int userId;
	
}

