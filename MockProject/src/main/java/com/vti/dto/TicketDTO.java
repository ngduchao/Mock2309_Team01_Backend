package com.vti.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketDTO {
	
	private Integer id;
	
	private Integer quantity;
	
	private Integer total;
	
	private Integer creatorId;
	
	private Integer filmScheduleId;
}
