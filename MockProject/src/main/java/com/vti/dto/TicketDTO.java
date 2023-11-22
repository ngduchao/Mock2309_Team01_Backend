package com.vti.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.entity.Ticket.TicketKey;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketDTO {
	
	private TicketKey id;
	
	private Integer quantity;
	
	private Integer total;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date bookingDate;
}
