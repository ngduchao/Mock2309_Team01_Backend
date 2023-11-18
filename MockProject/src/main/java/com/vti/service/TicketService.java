package com.vti.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.entity.Ticket;
import com.vti.form.ticket.CreatingTicketForm;
import com.vti.repository.ITicketRepository;

@Service
public class TicketService implements ITicketService{

    @Autowired
    private ITicketRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createTicket(CreatingTicketForm form) {
        Ticket ticket = modelMapper.map(form, Ticket.class);
        repository.save(ticket);
    }
    
}
