package com.vti.form.film;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.validation.film.FilmIDExists;
import com.vti.validation.film.FilmNameNotExists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatingFilmForm {
	
	@NotBlank(message = "{Film.updateFilm.form.id.NotBlank}")
	@FilmIDExists(message = "{Film.updateFilm.form.id.NotExists}")
	private Integer id;
	
	@NotBlank(message = "{Film.createFilm.form.name.NotBlank}")
	@Length(max = 100, message = "{Film.createFilm.form.name.Length}")
	@FilmNameNotExists(message = "{Film.createFilm.form.name.NotExists}")
	private String name;
	
	@NotBlank(message = "{Film.createFilm.form.directors.NotBlank}")
	@Length(max = 50, message = "{Film.createFilm.form.directors.Length}")
	private String directors;
	
	@NotBlank(message = "{Film.createFilm.form.actors.NotBlank}")
	@Length(max = 200, message = "{Film.createFilm.form.actors.Length}")
	private String actors;
	
	@NotBlank(message = "{Film.createFilm.form.genre.NotBlank}")
	@Length(max = 100, message = "{Film.createFilm.form.genre.Length}")
	private String genre;
	
	@NotBlank(message = "{Film.createFilm.form.duration.NotBlank}")
	@Length(max = 30, message = "{Film.createFilm.form.duration.Length}")
	private String duration;
	
	@NotBlank(message = "{Film.createFilm.form.description.NotBlank}")
	@Length(max = 200, message = "{Film.createFilm.form.description.Length}")
	private String description;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date releaseDate;
	
	@NotBlank(message = "{Film.createFilm.form.ticketPrice.NotBlank}")
	private Integer ticketPrice;
	
	@NotBlank(message = "{Film.createFilm.form.poster.NotBlank}")
	private String poster;
}
