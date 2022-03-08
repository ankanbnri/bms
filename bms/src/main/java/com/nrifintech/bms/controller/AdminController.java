package com.nrifintech.bms.controller;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nrifintech.bms.entity.Bus;
import com.nrifintech.bms.entity.Route;
import com.nrifintech.bms.entity.Ticket;
import com.nrifintech.bms.service.BusService;
import com.nrifintech.bms.service.RouteService;
import com.nrifintech.bms.service.TicketService;
import com.nrifintech.bms.entity.User;
import com.nrifintech.bms.exporter.DepartureSheetExporter;
import com.nrifintech.bms.service.UserService;
import com.nrifintech.bms.util.AdminBusSortingUtils;
import com.nrifintech.bms.util.BusActiveStatus;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private BusService busService;
	@Autowired
	private UserService userService;
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private RouteService routeService;
	
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
	public ModelAndView displayBusInformation(@RequestParam(required = false, name = "sort") String sort)
	{
		List<Bus> buses = null;
		if (sort != null) {
			int theSortField = Integer.parseInt(sort);
			buses = busService.getBuses(theSortField);
		} else {
			buses= busService.getBuses(AdminBusSortingUtils.REGISTRATION_NO);
		}
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
		Bus bus = busService.getById(registrationNo);
		bus.setActiveStatus(BusActiveStatus.NO);
		busService.saveOrUpdate(bus);
		ModelAndView mv = new ModelAndView("redirect:/admin/displayBusInformation");
		return mv;
	}
	@GetMapping("/enableBus/{registrationNo}")
	public ModelAndView enableBus(@PathVariable("registrationNo") String registrationNo )
	{
		Bus bus = busService.getById(registrationNo);
		bus.setActiveStatus(BusActiveStatus.YES);
		busService.saveOrUpdate(bus);
		ModelAndView mv = new ModelAndView("redirect:/admin/displayBusInformation");
		return mv;
	}
	
	@RequestMapping("ticketList/{registrationNo}")
	public ModelAndView ticketList(@PathVariable("registrationNo") String registrationNo) {
		Date today = new Date();
		Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
		Bus bus = busService.getById(registrationNo);
		List<Ticket> tickets = ticketService.findAllTicketsByBusAndDateBought(bus, tomorrow);
		ModelAndView modelAndView = new ModelAndView("ticketList");
		modelAndView.addObject(bus);
		if(tickets.isEmpty()) {
			modelAndView.addObject("ticketFound", false);
		}
		else {
			modelAndView.addObject("ticketFound", true);
			modelAndView.addObject("tickets", tickets);
		}
		return  modelAndView;
	}
	
	@RequestMapping("/export/{registrationNo}")
	public void exportToExcel(@PathVariable("registrationNo") String registrationNo, HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		HttpSession session = request.getSession();
		Object attribute = session.getAttribute("isValidAdmin");
		if(attribute != (Object)true) {
			response.sendRedirect(request.getContextPath()+"/admin/login");
		}
		else {
			Date today = new Date();
			Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
			String tmrDate = tomorrow.toString().substring(0, 10);
			Bus bus = busService.getById(registrationNo);
			List<Ticket> tickets = ticketService.findAllTicketsByBusAndDateBought(bus, tomorrow);
			
			// DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			String file_name = bus.getBusName() + tmrDate + ".xls";
			response.setContentType("application/vnd.ms-excel");
	        response.setHeader("Content-Disposition", "attachment; filename="+file_name);
		
			DepartureSheetExporter excelExporter = new DepartureSheetExporter(tickets);
			excelExporter.export(response);
		}
		
	}
	
	
	// For calling admin add bus form
		@GetMapping("/addBus")
		public ModelAndView welcomeUser() {
			ModelAndView mv= new ModelAndView("AdminAddBus");
			return mv;
		}
	
     // For saving the data in database after getting from form
	@PostMapping("/saveBus")
	public ModelAndView addUser(@ModelAttribute("bus") Bus bus, @ModelAttribute("startTimeForm") String startTimeForm , @ModelAttribute("routeCode") int routeCode )
	{
		Bus busExists = busService.findByRegistrationNo(bus.getRegistrationNo());
		if(busExists != null) {
			ModelAndView uauth = new ModelAndView("AdminAddBus");
			uauth.addObject("error_msg", "Already registered bus with this Registration Number!");
			return uauth;
		}
		ModelAndView modelAndView  = new ModelAndView("redirect:/admin/addBus");
		
		Route route = routeService.getById(routeCode);
		bus.setRoute(route);
		bus.setActiveStatus(BusActiveStatus.YES);
		
		
		startTimeForm = startTimeForm.concat(":00");
		java.sql.Time time =  java.sql.Time.valueOf(startTimeForm);
		bus.setStartTime(time);
		
	
		busService.saveOrUpdate(bus);
		return modelAndView;
	} 
}
