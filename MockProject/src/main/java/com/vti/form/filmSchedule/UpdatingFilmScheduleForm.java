package com.vti.form.filmSchedule;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatingFilmScheduleForm {
	
	@Min(value = 0, message = "The seatnumber must greater than 0")
	@Max(value = 101, message = "The seatnumber must less than 101")
	private Integer seatNumber;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date timeSlot;
	
}