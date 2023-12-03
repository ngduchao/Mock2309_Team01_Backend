package com.vti.form.ticket;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.vti.entity.Ticket;
import com.vti.validation.filmSchedule.FilmScheduleIDExists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingTicketForm {

	@NotNull(message = "The quantity mustn't be null value")
	@Min(value = 0, message = "The quantity must greater than 0")
	@Max(value = 100, message = "The quantity must less than 100")
	private Integer quantity;

	private Integer creatorId;
	
	@NotNull(message = "The film schedule mustn't be null value")
	@FilmScheduleIDExists
	private Integer filmScheduleId;
	
	private Integer total;
    
	public Ticket toEntity() {
        return new Ticket(creatorId, filmScheduleId, quantity, total);
    }
}
