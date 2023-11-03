package com.vti.form.film;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.vti.entity.Film;
import com.vti.entity.User;
import com.vti.validation.film.FilmNameNotExists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingFilmForm {
	@NotBlank(message = "{Phim.createPhim.form.name.NotBlank}")
	@Length(max = 30, message = "{Phim.createPhim.form.name.Length}")
	@FilmNameNotExists(message = "{Phim.createPhim.form.name.NotExists}")
	private String name;

	@NotBlank(message = "{Phim.createPhim.form.daoDien.NotBlank}")
	@Length(min=6, max=50, message = "{Phim.createPhim.form.daoDien.Length}")
	private String directors;
	
	@NotBlank(message = "{Phim.createPhim.form.daoDien.NotBlank}")
	@Length(max=200, message = "{Phim.createPhim.form.daoDien.Length}")
	private String actors;
	
	@NotBlank(message = "{Phim.createPhim.form.daoDien.NotBlank}")
	@Length(max=100, message = "{Phim.createPhim.form.daoDien.Length}")
	private String genre;
	
	@NotBlank(message = "{Phim.createPhim.form.daoDien.NotBlank}")
	@Length(max=30, message = "{Phim.createPhim.form.daoDien.Length}")
	private String duration;
	
	@NotBlank(message = "{Phim.createPhim.form.daoDien.NotBlank}")
	@Length(max=200, message = "{Phim.createPhim.form.daoDien.Length}")
	private String description;
	
	@NotBlank(message = "{Phim.createPhim.form.daoDien.NotBlank}")
	@Length(max=800, message = "{Phim.createPhim.form.daoDien.Length}")
	private String poster;
	
	@NotNull(message = "{Phim.createPhim.form.daoDien.NotBlank}")
	private Integer ticketPrice;
	
	@NotNull(message = "{Phim.createPhim.form.daoDien.NotBlank}")
	private Date releaseDate;
	
	
	private User user = new User();
	
	public Film toEntity() {
		return new Film(name, directors, actors, genre, duration, description,
				ticketPrice, releaseDate, poster, user);
	}
}
