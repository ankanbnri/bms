package com.nrifintech.bms.repository;

import org.springframework.stereotype.Repository;

import com.nrifintech.bms.entity.Ticket;

@Repository
public interface TicketRepository extends AbstractBaseRepository<Ticket, String> {
}
