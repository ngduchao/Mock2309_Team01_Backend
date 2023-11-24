package com.vti.form.filmSchedule;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.dto.UserDTO.FilmInfoDTO;
import com.vti.entity.FilmSchedule;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingFilmSchedule {
	
	@NotNull(message = "The seatnumber mustn't be null value")
	@Min(value = 0, message = "The seatnumber must greater than 0")
	@Max(value = 101, message = "The seatnumber must less than 101")
	private Integer seatNumber;
	
	@NotNull(message = "The timeslot mustn't be null value")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date timeSlot;
	
	public FilmSchedule toEntity() {
		return new FilmSchedule(seatNumber, timeSlot);
	}
}
