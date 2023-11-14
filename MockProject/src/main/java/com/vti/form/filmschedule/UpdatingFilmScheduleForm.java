package com.vti.form.filmschedule;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatingFilmScheduleForm {
	private Integer seatNumber;

	private Date timeSlot;
}
