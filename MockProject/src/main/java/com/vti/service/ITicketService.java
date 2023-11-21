package com.vti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.dto.TicketInfoDTO;
import com.vti.entity.Ticket;
import com.vti.entity.User;
import com.vti.form.ticket.CreatingTicketForm;
import com.vti.form.ticket.TicketFilterForm;
import com.vti.form.ticket.UpdatingTicketForm;

public interface ITicketService {
	
	public Page<Ticket> getAllTickets(Pageable pageable, TicketFilterForm filter);
	
	public void updateTicket(User user, Integer filmScheduleId, UpdatingTicketForm form);
	
	public void deleteTicket(User user, Integer filmScheduleId);
	
	public void createTicket(CreatingTicketForm form);
	
	public List<Ticket> getTicketByUser(User user);
}
