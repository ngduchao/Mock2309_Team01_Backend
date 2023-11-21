package com.vti.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketInfoDTO {
	
	private Integer quantity;
	
	private Integer total;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date bookingDate;
}
