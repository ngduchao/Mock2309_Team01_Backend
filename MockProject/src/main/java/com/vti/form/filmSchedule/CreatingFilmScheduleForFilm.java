package com.vti.form.filmSchedule;

import javax.validation.constraints.NotNull;

import com.vti.validation.film.FilmIDExists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingFilmScheduleForFilm extends CreatingFilmSchedule{
<<<<<<< HEAD
	
	@NotNull(message = "The film id mustn't be null value")
=======
	@NotNull
>>>>>>> 05e0f3adf855167a4a3462692b9013eceaea1c7b
	@FilmIDExists
	private Integer filmId;
	
	
<<<<<<< HEAD
}
=======
}
>>>>>>> 05e0f3adf855167a4a3462692b9013eceaea1c7b
