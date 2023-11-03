package com.vti.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilmScheduleDTO {
	
	private Integer scheduleId;
	
	private Integer seatNumber;
	
	private Date timeSlot;
	
	private Integer filmId;
	
	private List<TicketDTO> tickets;
}
