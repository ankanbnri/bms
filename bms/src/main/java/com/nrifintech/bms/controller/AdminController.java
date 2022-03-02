package com.nrifintech.bms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@GetMapping("/dashboard")
	public ModelAndView getDashboard()
	{
		ModelAndView mv = new ModelAndView("AdminDashboard");
		return mv;
	}
	
	@GetMapping("/displayBusInformation")
	public ModelAndView displayBusInformation()
	{
		ModelAndView mv = new ModelAndView("AdminBusInformation");
		return mv;
	}
}
