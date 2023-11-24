package com.vti.form.filmSchedule;

import javax.validation.constraints.NotNull;

import com.vti.validation.film.FilmIDExists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingFilmScheduleForFilm extends CreatingFilmSchedule{
	
	@NotNull(message = "The film id mustn't be null value")
	@FilmIDExists
	private Integer filmId;
	
	
}