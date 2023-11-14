package com.vti.form.film;

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
	
	public Film toEntity() {
		return new Film(name, directors, actors, genre, duration, description, ticketPrice, poster);
	}
	
	
}
