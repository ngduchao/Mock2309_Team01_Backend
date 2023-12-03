package com.vti.form.filmSchedule;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilmScheduleFilterForm {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date minTimeSlot;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date maxTimeSlot;
	
}
