package com.nrifintech.bms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrifintech.bms.repository.TicketRepository;
import com.nrifintech.bms.entity.Bus;
import com.nrifintech.bms.entity.Ticket;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	public List<Ticket> findAllTicketsByBusAndDateBought(Bus bus, Date date){
		return ticketRepository.findAllTicketsByBusAndDateBought(bus, date);
	}
}
