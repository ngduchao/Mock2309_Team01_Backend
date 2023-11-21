package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vti.entity.Ticket;

public interface ITicketRepository extends JpaRepository<Ticket, Integer>, JpaSpecificationExecutor<Ticket>{

}
