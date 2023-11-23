package com.vti.form.ticket;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketFilterForm {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date minBookingDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date maxBookingDate;
}
