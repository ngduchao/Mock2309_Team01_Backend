package com.vti.form.film;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.vti.validation.film.FilmNameNotExists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatingFilmForm {
	
	@Length(max = 100, message = "The film name's length is max 100 characters")
//	@FilmNameNotExists
	private String name;
	
	@Length(max = 50, message = "The directors's length is max 50 characters")
	@Length(min = 6, message = "The directors's length is min 6 characters")
	private String directors;
	
	@Length(max = 200, message = "The actors's length is max 200 characters")
	private String actors;
	
	@Length(max = 100, message = "The genre's length is max 100 characters")
	private String genre;
	
	@Length(max = 30, message = "The duration's length is max 30 characters")
	private String duration;
	
	private String description;
	
	@Min(value = 0, message = "The ticket price must greater than 0 VND")
	private Integer ticketPrice;
	
	private String poster;
}
