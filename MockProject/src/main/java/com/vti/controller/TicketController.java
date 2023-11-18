package com.vti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.entity.User;
import com.vti.service.ITicketService;
import com.vti.service.IUserService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/tickets")
public class TicketController {
	
	@Autowired
	private ITicketService service;
	
	@Autowired
	private IUserService userService;
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteUser(Authentication authentication, @PathVariable(name = "id") Integer filmScheduleId){
		User user = userService.findUserByUsername(authentication.getName());
		service.deleteTicket(user,filmScheduleId);
		return new ResponseEntity<>("Delete Successfully!", HttpStatus.OK);
	}
}
