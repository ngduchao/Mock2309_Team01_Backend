package com.vti.form.filmSchedule;

import javax.validation.constraints.NotNull;

import com.vti.validation.film.FilmIDExists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingFilmScheduleForFilm extends CreatingFilmSchedule{
	@NotNull
	@FilmIDExists
	private Integer filmId;
	
	
}
