package com.nrifintech.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrifintech.bms.entity.Route;
import com.nrifintech.bms.repository.RouteRepository;

@Service
public class RouteService {
	
	@Autowired
	private RouteRepository repository;

	public List<String> getDistinctRouteStartName() {
		return repository.findDistinctRouteStartName();
	}

	public List<String> getDistinctRouteStopName() {
		return repository.findDistinctRouteStopName();
	}
	
	public Route getById(int id){
		return repository.getById(id);
	}

	
	
	
	
}
