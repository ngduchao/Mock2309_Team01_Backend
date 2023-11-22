package com.vti.form.filmSchedule;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatingFilmScheduleForm {
	
	private Integer seatNumber;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date timeSlot;
	
}