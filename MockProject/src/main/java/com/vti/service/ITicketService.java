package com.vti.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.form.ticket.CreatingTicketForm;

public interface ITicketService {

    // public Page<Ticket> getAllTickets(Pageable pageable, TicketFilterForm filter);

    // public Ticket getTicketByID(Integer id);

    // public void updateTicket(Integer id, Ticket ticket);

    public void createTicket(CreatingTicketForm form);

    // public boolean isTicketExistsByID(Integer id);

    // public void deleteTicket(Integer id);
    
}
