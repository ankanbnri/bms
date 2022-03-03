package com.nrifintech.bms.controller;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nrifintech.bms.entity.Bus;
import com.nrifintech.bms.service.BusService;
import com.nrifintech.bms.entity.User;
import com.nrifintech.bms.service.UserService;
import com.nrifintech.bms.util.BusActiveStatus;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private BusService busService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public ModelAndView adminLogin() {
		ModelAndView mv = new ModelAndView("AdminLogin");
		return mv;
	}
	
	@GetMapping("/dashboard")
	public ModelAndView adminDashboard() {
		ModelAndView mv = new ModelAndView("AdminDashboard");
		return mv;
	}
	
	@GetMapping("/displayBusInformation")
	public ModelAndView displayBusInformation()
	{
		List<Bus> buses = busService.findAll();
		ModelAndView mv = new ModelAndView("AdminBusInformation");
		Date today = new Date();
		Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
		String tmrDate = tomorrow.toString().substring(0, 10);
		mv.addObject("tmrDate", tmrDate);
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
	@PostMapping("/login")
	public ModelAndView doAdminLogin(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		email = email.toLowerCase();
		String password = request.getParameter("password");
		User user = userService.findAdmin(email);
		
		if (user == null) {
			ModelAndView uauth = new ModelAndView("AdminLogin");
			uauth.addObject("error_msg", "Please provide correct admin email");
			uauth.addObject("email", email);
			return uauth;
		}
		else {
			boolean isValidAdmin = userService.isValidUser(user, password);
			if(isValidAdmin) {
				HttpSession session = request.getSession();
				session.setAttribute("isValidAdmin", true);
				session.setAttribute("userid", user.getUserid());
				session.setAttribute("email", user.getEmail());
				session.setAttribute("name", user.getName());
				ModelAndView mv = new ModelAndView("redirect:/admin/dashboard");
				return mv;
			}
			else {
				ModelAndView uauth = new ModelAndView("AdminLogin");
				uauth.addObject("error_msg", "Invalid Password. Please try again");
				uauth.addObject("email", email);
				return uauth;
			}
		}
	}
	
	@GetMapping("/logout")
	public ModelAndView doLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("isValidAdmin");
		session.removeAttribute("userid");
		session.removeAttribute("email");
		session.removeAttribute("name");
		session.invalidate();
		ModelAndView mv = new ModelAndView("redirect:/user/welcome");
		return mv;
	}
	
	@GetMapping("/disableBus/{registrationNo}")
	public ModelAndView disableBus(@PathVariable("registrationNo") String registrationNo )
	{
		System.out.println(registrationNo);
		Bus bus = busService.getById(registrationNo);
		bus.setActiveStatus(BusActiveStatus.NO);
		busService.saveOrUpdate(bus);
		ModelAndView mv = new ModelAndView("redirect:/admin/displayBusInformation");
		return mv;
	}

}
