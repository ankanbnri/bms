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

	public List<Bus> getBusWithSourceDest(String source, String destination) {
		return repository.findAllWithSourceDest(source, destination); 
	}
}
