package com.vti.form.film;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.vti.entity.Film;
import com.vti.validation.film.FilmNameNotExists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingFilmForm {
	
	@NotBlank(message = "The film name mustn't be null value")
	@Length(max = 100, message = "The film name's length is max 100 characters")
	@FilmNameNotExists
	private String name;

	@NotBlank(message = "The directors mustn't be null value")
	@Length(max = 50, message = "The directors's length is max 50 characters")
	@Length(min = 6, message = "The directors's length is min 6 characters")
	private String directors;
	
	@NotBlank(message = "The actors mustn't be null value")
	@Length(max = 200, message = "The actors's length is max 200 characters")
	private String actors;
	
	@NotBlank(message = "The genre mustn't be null value")
	@Length(max = 100, message = "The genre's length is max 100 characters")
	private String genre;
	
	@NotBlank(message = "The duration mustn't be null value")
	@Length(max = 30, message = "The duration's length is max 30 characters")
	private String duration;
	
	@NotBlank(message = "The description mustn't be null value")
	private String description;

	@NotNull(message = "The duration mustn't be null value")
	@Min(value = 0, message = "The ticket price must greater than 0 VND")
	private Integer ticketPrice;

	@NotBlank(message = "The poster mustn't be null value")
	private String poster;
	
	public Film toEntity() {
		return new Film(name, directors, actors, genre, duration, description, ticketPrice, poster);
	}
}
