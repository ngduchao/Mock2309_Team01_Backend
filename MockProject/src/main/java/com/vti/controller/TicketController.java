package com.vti.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.form.ticket.CreatingTicketForm;
import com.vti.service.ITicketService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/tickets")
public class TicketController {
    @Autowired
    private ITicketService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping()
    public ResponseEntity<?> createTicket(@RequestBody CreatingTicketForm form) {
        service.createTicket(form);
        return new ResponseEntity<String>("Create successfully!", HttpStatus.OK);
    }
}
