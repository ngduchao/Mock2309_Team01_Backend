package com.vti.form.ticket;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatingTicketForm {
	
	@Min(value = 0, message = "The quantity must greater than 0")
	@Max(value = 100, message = "The quantity must less than 0")
	private Integer quantity;
	
	private Integer total;

}
