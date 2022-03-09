package com.nrifintech.bms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
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
import com.nrifintech.bms.service.EmailSenderService;
import com.nrifintech.bms.service.TicketService;
import com.nrifintech.bms.service.UserService;
import com.nrifintech.bms.util.TicketEmailTemplate;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	private BusService busService;
	@Autowired
	private UserService userService;
	@Autowired
	TicketService ticketService;
	@Autowired
	private EmailSenderService emailSenderService;

	@GetMapping("/bookTicket/{regNo}/{travelDate}/{availableSeats}")
	public ModelAndView showBookingInfo(@PathVariable("regNo") String regNo,
			@PathVariable("travelDate") String travelDate, @PathVariable("availableSeats") int availableSeats) {

//		System.out.println("book ticket get api....");
//		System.out.println(regNo);
//		System.out.println(travelDate);
		Bus bus = busService.getById(regNo);
		ModelAndView modelAndView = new ModelAndView("bookTicket");
		modelAndView.addObject("bus", bus);
		modelAndView.addObject("travelDate", travelDate);
		modelAndView.addObject("availableSeats", availableSeats);
		return modelAndView;
	}

	@PostMapping("/bookTicket/{regNo}/{travelDate}")
	public ModelAndView saveBookingInfo(@PathVariable("regNo") String regNo,
			@PathVariable("travelDate") String travelDate, @ModelAttribute("ticket") Ticket ticket,
			HttpServletRequest request) throws ParseException {

		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userid");
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
		TicketEmailTemplate ticketTemplate = new TicketEmailTemplate(user.getName(),ticket.getPnrNo(),
				ticket.getDateBought().toString(), ticket.getDateOfTravel().toString(), bus.getRegistrationNo(),
				bus.getBusName(), bus.getFacilities().toString(), bus.getStartTime().toString(),
				bus.getRoute().getStartName(), bus.getRoute().getStopName(), ticket.getSeatsBooked(),
				ticket.getTotalAmount());
		emailSenderService.sendEmail(user.getEmail(), ticketTemplate.toString());
		ModelAndView modelAndView = new ModelAndView("redirect:/user/myTickets");
		return modelAndView;
	}
	
	@GetMapping("/cancel/{pnrNo}")
	public ModelAndView cancelTicket(@PathVariable("pnrNo") String pnrNo) {
		
		ticketService.deleteByID(pnrNo);
		ModelAndView modelAndView = new ModelAndView("redirect:/user/myTickets");
		return modelAndView;
	}

}
