package com.vti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.entity.Ticket;
import com.vti.entity.User;
import com.vti.repository.ITicketRepository;

@Service
public class TicketService implements ITicketService {

	@Autowired
	private ITicketRepository repository;

	@Override
	public void deleteTicket(User user, Integer filmScheduleId) {
		List<Ticket> tickets = user.getTickets();
		Ticket deleteTicket = new Ticket();
		for(Ticket ticket: tickets) {
			if(ticket.getFilmSchedule().getScheduleId() == filmScheduleId)
				deleteTicket = ticket;
		}
		repository.delete(deleteTicket);
	}
}
