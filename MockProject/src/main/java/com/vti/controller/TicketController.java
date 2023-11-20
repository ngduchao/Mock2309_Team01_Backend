package com.vti.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.TicketDTO;
import com.vti.entity.Ticket;
import com.vti.entity.User;
import com.vti.form.ticket.CreatingTicketForm;
import com.vti.form.ticket.TicketFilterForm;
import com.vti.form.ticket.UpdatingTicketForm;
import com.vti.service.ITicketService;
import com.vti.service.IUserService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/v1/tickets")
public class TicketController {
	
	@Autowired
	private ITicketService service;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public ResponseEntity<?> getAllTickets (
			Pageable pageable,
			TicketFilterForm filter){
		
		Page<Ticket> entitiesPages = service.getAllTickets(pageable, filter);
		
		List<TicketDTO> dtos = modelMapper.map(entitiesPages.getContent(), new TypeToken<List<TicketDTO>>() {}.getType());
		
		Page<TicketDTO> dtoPages = new PageImpl<>(dtos, pageable, entitiesPages.getTotalElements());
		
		return new ResponseEntity<>(dtoPages, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateTicket(
			Authentication authentication, 
			@PathVariable(name = "id") Integer filmScheduleId, 
			@RequestBody UpdatingTicketForm form){
		
		User user = userService.findUserByUsername(authentication.getName());
		
		service.updateTicket(user, filmScheduleId, form);
		
		return new ResponseEntity<>("Update Successfully!", HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteUser(
			Authentication authentication, 
			@PathVariable(name = "id") Integer filmScheduleId){
		
		User user = userService.findUserByUsername(authentication.getName());
		
		service.deleteTicket(user,filmScheduleId);
		
		return new ResponseEntity<>("Delete Successfully!", HttpStatus.OK);
	}
	
//	@PostMapping(value = "/{filmScheduleId}")
//	public ResponseEntity<?> createTicket(
//			Authentication authentication,
//			@RequestBody CreatingTicketForm form,
//			@PathVariable(name = "filmScheduleId") Integer filmScheduleId){
//		
//		User user = userService.findUserByUsername(authentication.getName());
//		
//		form.setCreatorId(user.getId());
//		
//		form.setFilmScheduleId(filmScheduleId);
//		
//		service.createTicket(user, form);
//		
//		return new ResponseEntity<>("Create successfully!", HttpStatus.OK);
//	}
	
	@PostMapping()
    public ResponseEntity<?> createTicket(Authentication authentication, @RequestBody CreatingTicketForm form) {
		
        String username = authentication.getName();
        
        User userInfo = userService.findUserByUsername(username);
        
        form.setCreatorId(userInfo.getId());
        
        service.createTicket(form);
        
        return new ResponseEntity<>("Create successfully!", HttpStatus.OK);
    }
}


