package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vti.entity.Ticket;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket, Long> {

}
