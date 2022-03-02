package com.nrifintech.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.nrifintech.bms.entity.Bus;
import com.nrifintech.bms.service.BusService;
import com.nrifintech.bms.service.RouteService;

@Controller
@RequestMapping("/user")
public class SearchBusController {
	
	@Autowired
	private BusService busService;
	@Autowired
	private RouteService routeService;
	
	@GetMapping("/searchBus")
	public ModelAndView showSearchBusForm() {
		
		List<String> startNames = routeService.getDistinctRouteStartName();
//		System.out.println(startNames);
		List<String> stopNames = routeService.getDistinctRouteStopName();
//		System.out.println(stopNames);
		
		ModelAndView modelAndView = new ModelAndView("searchBus");
		modelAndView.addObject("startNames",startNames);
		modelAndView.addObject("stopNames",stopNames);
		return modelAndView;
	}
	
	@PostMapping("/searchBus")
	public ModelAndView searchBus(@ModelAttribute("source") String source,
			@ModelAttribute("destination") String destination,
			@ModelAttribute("travelDate") String travelDate) {
		System.out.println(source);
		System.out.println(destination);
		System.out.println(travelDate);
		List<Bus> buses = busService.getBusWithSourceDest(source,destination);
//		System.out.println(buses);
//		for(Bus bus: buses) {
//			System.out.println(bus.getBusName());
//		}
		// addObject & set view
		ModelAndView modelAndView = new ModelAndView("listBus");
		if(buses.size()>0) {
			modelAndView.addObject("buses",buses);
			modelAndView.addObject("travelDate",travelDate);
			modelAndView.addObject("busFound", true);
			return modelAndView;
		}else {
			modelAndView.addObject("busFound", false);
			return modelAndView;
		}
		
	}
	
	
}

