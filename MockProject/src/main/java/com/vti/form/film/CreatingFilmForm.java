package com.vti.form.film;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.entity.Film;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingFilmForm {
	
	private String name;

	private String directors;
	
	private String actors;
	
	private String genre;
	
	private String duration;
	
	private String description;

	private Integer ticketPrice;

	private String poster;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date releaseDate;
	
	public Film toEntity() {
		
		return new Film(name, directors, actors, genre, duration, description, ticketPrice, poster, releaseDate);

	}
}
