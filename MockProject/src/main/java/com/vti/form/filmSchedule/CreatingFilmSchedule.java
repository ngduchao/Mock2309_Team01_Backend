package com.vti.form.filmSchedule;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.entity.FilmSchedule;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingFilmSchedule {
	
	private Integer seatNumber;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date timeSlot;
	
	public FilmSchedule toEntity() {
		return new FilmSchedule(seatNumber, timeSlot);
	}
}
