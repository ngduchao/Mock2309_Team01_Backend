package com.vti.form.film;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.entity.Film;
import com.vti.entity.User;
import com.vti.validation.film.FilmNameNotExists;

import lombok.Data;
import lombok.NoArgsConstructor;

public class CreatingFilmForm {
	
	private String name;

	private String directors;
	
	private String actors;
	
	private String genre;
	
	private String duration;
	
	private String description;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date releaseDate;

	private Integer ticketPrice;

	private String poster;
	
	private UserInfo user;
	
	@Data
	@NoArgsConstructor
	public static class UserInfo{
		private Integer id;
		
		private String name;
	}
	
	public Film toEntity() {
		return new Film(name, directors, actors, genre, duration, description, ticketPrice, poster);
	}
}
