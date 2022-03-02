package com.nrifintech.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nrifintech.bms.entity.Bus;
import com.nrifintech.bms.service.BusService;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired
	private BusService busService;
	
	@GetMapping("/dashboard")
	public ModelAndView getDashboard()
	{
		ModelAndView mv = new ModelAndView("AdminDashboard");
		return mv;
	}
	
	@GetMapping("/displayBusInformation")
	public ModelAndView displayBusInformation()
	{
		List<Bus> buses = busService.findAll();
		ModelAndView mv = new ModelAndView("AdminBusInformation");
		if(buses.isEmpty())
		{
			mv.addObject("busFound", false);
		}
		else
		{
			mv.addObject("busFound", true);
			mv.addObject("buses",buses);
		}		
		return mv;
	}
}
