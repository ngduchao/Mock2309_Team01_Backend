package com.vti.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vti.dto.TicketInfoDTO;
import com.vti.entity.Film;
import com.vti.entity.FilmSchedule;
import com.vti.entity.Ticket;
import com.vti.entity.User;
import com.vti.form.ticket.CreatingTicketForm;
import com.vti.form.ticket.TicketFilterForm;
import com.vti.form.ticket.UpdatingTicketForm;
import com.vti.repository.IFilmScheduleRepository;
import com.vti.repository.ITicketRepository;
import com.vti.specification.ticket.TicketSpecification;

@Service
public class TicketService implements ITicketService{
	
	@Autowired
	private ITicketRepository repository;
	
	@Autowired
	private IFilmScheduleRepository filmScheduleRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Page<Ticket> getAllTickets(Pageable pageable, TicketFilterForm filter) {
		
		Specification<Ticket> where = TicketSpecification.buildWhere(filter);
		
		return repository.findAll(where, pageable);
	}

	@Override
	public void updateTicket(User user, Integer filmScheduleId, UpdatingTicketForm form) {
		
		List<Ticket> tickets = user.getTickets();
		
		Ticket newTicket = new Ticket();
		
		Film film = filmScheduleRepository.getById(filmScheduleId).getFilm();
		
		for(Ticket ticket : tickets) {
			if(ticket.getFilmSchedule().getScheduleId() == filmScheduleId) {
				ticket.setQuantity(form.getQuantity());
				Integer total = form.getQuantity() * film.getTicketPrice();
				ticket.setTotal(total);
				
				newTicket = ticket;
			}
		}
		repository.save(newTicket);
	}

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

	@Override
	public void createTicket(CreatingTicketForm form) {
		
		Ticket ticket = new Ticket();
		
		FilmSchedule filmSchedule = filmScheduleRepository.getById(form.getFilmScheduleId());
		
		Film film = filmScheduleRepository.getById(filmSchedule.getScheduleId()).getFilm();
		
		ticket = modelMapper.map(form, Ticket.class);
		
		//lấy ra giá vé của phim
		Integer total = form.getQuantity() * film.getTicketPrice();
		ticket.setTotal(total);
				
        repository.save(ticket);
	}

	@Override
	public List<Ticket> getTicketByUser(User user) {
		
		List<Ticket> tickets = user.getTickets();
		
		return tickets;
	}

}
