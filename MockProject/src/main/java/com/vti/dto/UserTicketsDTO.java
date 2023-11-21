package com.vti.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.entity.Ticket.TicketKey;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserTicketsDTO {
	private List<TicketDTO> tickets;
	
	@Data
	@NoArgsConstructor
	public static class TicketDTO{
		
		private TicketKey id;
		
		private Integer quantity;
		
		private Integer total;
		
		@JsonFormat(pattern = "dd-MM-yyyy")
		private Date bookingDate;
	}
}
