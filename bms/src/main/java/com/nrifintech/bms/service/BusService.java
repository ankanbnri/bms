package com.nrifintech.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrifintech.bms.entity.Bus;
import com.nrifintech.bms.repository.BusAndRouteRepository;

@Service
public class BusService {
	@Autowired
	private BusAndRouteRepository repository;
	@Autowired
	private TicketService ticketService;

	public List<Bus> getBusWithSourceDest(String source, String destination) {
		return repository.findAllWithSourceDest(source, destination); 
	}

	public Bus getById(String regNo) {
		return repository.getById(regNo);
	}

	public void setAllAvailableSeats(List<Bus> buses) {
		int availableSeats;
		for(Bus bus: buses) {
			availableSeats = bus.getSeatCount() - ticketService.getTotalSeatsByBus(bus);
//			System.out.println(availableSeats);
			bus.setAvailableSeats(availableSeats);
		}
	}
}
