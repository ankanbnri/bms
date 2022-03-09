package com.nrifintech.bms.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nrifintech.bms.entity.Bus;
import com.nrifintech.bms.entity.Ticket;
import com.nrifintech.bms.entity.User;
import com.nrifintech.bms.service.BusService;
import com.nrifintech.bms.service.TicketService;
import com.nrifintech.bms.service.UserService;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	private BusService busService;
	@Autowired
	private UserService userService;
	@Autowired
	private TicketService ticketService;
	
	@GetMapping("/bookTicket/{regNo}/{travelDate}/{availableSeats}")
	public ModelAndView showBookingInfo(@PathVariable("regNo") String regNo, 
            							@PathVariable("travelDate") String travelDate,
            							@PathVariable("availableSeats") int availableSeats) {
		
		Bus bus = busService.getById(regNo);
		ModelAndView modelAndView = new ModelAndView("bookTicket");
		modelAndView.addObject("bus", bus);
		modelAndView.addObject("travelDate", travelDate);
		modelAndView.addObject("availableSeats", availableSeats);
		return modelAndView;
	}
	
	@PostMapping("/bookTicket/{regNo}/{travelDate}")
	public ModelAndView saveBookingInfo(@PathVariable("regNo") String regNo, 
										@PathVariable("travelDate") String travelDate,
										@ModelAttribute("ticket") Ticket ticket,
										HttpServletRequest request) throws ParseException  {
		
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userid");
		User user = userService.getById(userId);
		Bus bus = busService.getById(regNo);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse(travelDate);
        java.sql.Date dateOfTravel = new java.sql.Date(parsed.getTime());
        
        
		ticket.setPnrNo(ticketService.generatePnrNo(userId));
		ticket.setDateOfTravel(dateOfTravel);
		ticket.setDateBought(java.sql.Date.valueOf(LocalDate.now()));
		ticket.setBus(bus);
		ticket.setUser(user);
		ticketService.save(ticket);
		ModelAndView modelAndView = new ModelAndView("redirect:/user/myTickets");
		return modelAndView;
	}
	
	@GetMapping("/cancel/{pnrNo}")
	public ModelAndView cancelTicket(@PathVariable("pnrNo") String pnrNo, HttpServletResponse response,
			HttpServletRequest request)  {
		
		HttpSession session = request.getSession();
		Object attribute = session.getAttribute("isValidUser");
		
		if (attribute != (Object) true) {
			return new ModelAndView("redirect:/user/login");
		} else {
			Ticket ticket = ticketService.getById(pnrNo);
			int userIdOfTicket = ticket.getUser().getUserid();
			
			if(userIdOfTicket == (Integer) session.getAttribute("userid")) {
				System.out.println("cancel ticket api...");
				ticketService.deleteByID(pnrNo);
			}
			
			ModelAndView modelAndView = new ModelAndView("redirect:/user/myTickets");
			return modelAndView;
		}
		
	}

}
