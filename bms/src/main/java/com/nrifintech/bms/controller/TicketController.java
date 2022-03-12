package com.nrifintech.bms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nrifintech.bms.entity.Bus;
import com.nrifintech.bms.entity.Ticket;
import com.nrifintech.bms.entity.User;
import com.nrifintech.bms.model.PaytmDetails;
import com.nrifintech.bms.service.BusService;
import com.nrifintech.bms.service.EmailSenderService;
import com.nrifintech.bms.service.TicketService;
import com.nrifintech.bms.service.UserService;
import com.nrifintech.bms.util.TicketEmailTemplate;

import com.paytm.pg.merchant.PaytmChecksum;

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
	@Autowired
	private PaytmDetails paytmDetails;

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

//	@PostMapping("/bookTicket/{regNo}/{travelDate}")
//	public ModelAndView saveBookingInfo(@PathVariable("regNo") String regNo,
//			@PathVariable("travelDate") String travelDate, @ModelAttribute("ticket") Ticket ticket,
//			HttpServletRequest request) throws ParseException {
//
//		HttpSession session = request.getSession();
//		int userId = (int) session.getAttribute("userid");
//		User user = userService.getById(userId);
//		Bus bus = busService.getById(regNo);
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date parsed = format.parse(travelDate);
//        java.sql.Date dateOfTravel = new java.sql.Date(parsed.getTime());
//        
//        
//		ticket.setPnrNo(ticketService.generatePnrNo(userId));
//		ticket.setDateOfTravel(dateOfTravel);
//		ticket.setDateBought(java.sql.Date.valueOf(LocalDate.now()));
//		ticket.setBus(bus);
//		ticket.setUser(user);
//		ticketService.save(ticket);
//		TicketEmailTemplate ticketTemplate = new TicketEmailTemplate(user.getName(),ticket.getPnrNo(),
//				ticket.getDateBought().toString(), ticket.getDateOfTravel().toString(), bus.getRegistrationNo(),
//				bus.getBusName(), bus.getFacilities().toString(), bus.getStartTime().toString(),
//				bus.getRoute().getStartName(), bus.getRoute().getStopName(), ticket.getSeatsBooked(),
//				ticket.getTotalAmount());
//		emailSenderService.sendEmail(user.getEmail(), ticketTemplate.toString());
//		ModelAndView modelAndView = new ModelAndView("redirect:/user/myTickets");
//		return modelAndView;
//	}

	@PostMapping("/bookTicket/{regNo}/{travelDate}")
	public ModelAndView saveBookingInfo(@PathVariable("regNo") String regNo,
			@PathVariable("travelDate") String travelDate, @ModelAttribute("ticket") Ticket ticket,
			HttpServletRequest request, RedirectAttributes redirectAttributes) throws ParseException {

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
		request.setAttribute("ticket", ticket);
		ModelAndView modelAndView = new ModelAndView("forward:/ticket/paymentProcessing");
		return modelAndView;
	}

	@PostMapping("/paymentProcessing")
	public ModelAndView paymentRedirect(@RequestAttribute("ticket") Ticket ticket, HttpServletRequest request)
			throws Exception {
		System.out.println("in payment processing");
		ModelAndView modelAndView = new ModelAndView("redirect:" + paytmDetails.getPaytmUrl());
		TreeMap<String, String> parameters = new TreeMap<>();
		paytmDetails.getDetails().forEach((k, v) -> parameters.put(k, v));
		parameters.put("MOBILE_NO", "8961249128");
		parameters.put("EMAIL", "beingakscool@gmail.com");
		parameters.put("ORDER_ID", ticket.getPnrNo());
		parameters.put("TXN_AMOUNT", String.valueOf(ticket.getTotalAmount()));
		parameters.put("CUST_ID", String.valueOf(ticket.getUser().getUserid()));
		String checkSum = getCheckSum(parameters);
		parameters.put("CHECKSUMHASH", checkSum);
		modelAndView.addAllObjects(parameters);
		HttpSession session = request.getSession();
		session.setAttribute("ticket", ticket);
		return modelAndView;
//		ticketService.save(ticket);
//		TicketEmailTemplate ticketTemplate = new TicketEmailTemplate.TicketEmailTemplateBuilder(ticket.getPnrNo(),
//				ticket.getSeatsBooked()).busName(ticket.getBus().getBusName())
//						.dateBought(ticket.getDateBought().toString()).dateofTravel(ticket.getDateOfTravel().toString())
//						.name(ticket.getUser().getName()).registrationNo(ticket.getBus().getRegistrationNo())
//						.busName(ticket.getBus().getBusName()).facilities(ticket.getBus().getFacilities().toString())
//						.startTime(ticket.getBus().getStartTime().toString())
//						.startName(ticket.getBus().getRoute().getStartName()).totalPaid(ticket.getTotalAmount())
//						.stopName(ticket.getBus().getRoute().getStopName()).build();
//		emailSenderService.sendEmail(ticket.getUser().getEmail(), ticketTemplate.toString());
//		ModelAndView modelAndView = new ModelAndView("redirect:/user/myTickets");
//		redirectAttributes.addFlashAttribute("bookedTicket", ticket);
//		return modelAndView;
	}

	@PostMapping("/paymentResponse")
	public ModelAndView PaymentProcessed(HttpServletRequest request, RedirectAttributes redirectAttributes)
			throws Exception {
		System.out.println("in payment res");
		HttpSession session = request.getSession(false);
		System.out.println(session.getCreationTime());
		Ticket ticket = (Ticket) session.getAttribute("ticket");
		System.out.println(ticket.getPnrNo());
		Map<String, String[]> mapData = request.getParameterMap();
		TreeMap<String, String> parameters = new TreeMap<String, String>();
		mapData.forEach((key, val) -> parameters.put(key, val[0]));
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("RESULT : " + parameters.toString());
		if (parameters.get("RESPCODE").equals("01")) {
			System.out.println("suceess");
			modelAndView.setViewName("redirect:/user/myTickets");
			ticketService.save(ticket);
			TicketEmailTemplate ticketTemplate = new TicketEmailTemplate.TicketEmailTemplateBuilder(ticket.getPnrNo(),
					ticket.getSeatsBooked()).busName(ticket.getBus().getBusName())
							.dateBought(ticket.getDateBought().toString())
							.dateofTravel(ticket.getDateOfTravel().toString()).name(ticket.getUser().getName())
							.registrationNo(ticket.getBus().getRegistrationNo()).busName(ticket.getBus().getBusName())
							.facilities(ticket.getBus().getFacilities().toString())
							.startTime(ticket.getBus().getStartTime().toString())
							.startName(ticket.getBus().getRoute().getStartName()).totalPaid(ticket.getTotalAmount())
							.stopName(ticket.getBus().getRoute().getStopName()).build();
			emailSenderService.sendEmail(ticket.getUser().getEmail(), ticketTemplate.toString());
			redirectAttributes.addFlashAttribute("bookedTicket", ticket);
		} else {
			System.out.println("fail");
			modelAndView.setViewName("PaymentFailed");
		}
		return modelAndView;
	}

//	@GetMapping("/cancel/{pnrNo}")
//	public ModelAndView cancelTicket(@PathVariable("pnrNo") String pnrNo, HttpServletResponse response,
//			HttpServletRequest request)  {
//		
//		HttpSession session = request.getSession();
//		Object attribute = session.getAttribute("isValidUser");
//		
//		if (attribute != (Object) true) {
//			return new ModelAndView("redirect:/user/login");
//		} else {
//			Ticket ticket = ticketService.getById(pnrNo);
//			int userIdOfTicket = ticket.getUser().getUserid();
//			
//			if(userIdOfTicket == (Integer) session.getAttribute("userid")) {
//				ticketService.deleteByID(pnrNo);
//			}
//			
//			ModelAndView modelAndView = new ModelAndView("redirect:/user/myTickets");
//			return modelAndView;
//		}
//		
//	}

	@GetMapping("/cancel/{pnrNo}")
	public ModelAndView cancelTicket(@PathVariable("pnrNo") String pnrNo, HttpServletResponse response,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {

		HttpSession session = request.getSession();
		Object attribute = session.getAttribute("isValidUser");

		if (attribute != (Object) true) {
			return new ModelAndView("redirect:/user/login");
		} else {
			Optional<Ticket> optionalTicket = ticketService.findById(pnrNo);
			Ticket ticket;
			if (optionalTicket.isPresent()) {
				ticket = optionalTicket.get();
			} else {
				ticket = null;
			}

			if (ticket != null) {
				int userIdOfTicket = ticket.getUser().getUserid();

				if (userIdOfTicket == (Integer) session.getAttribute("userid")) {
					redirectAttributes.addFlashAttribute("pnrNo", ticket.getPnrNo());
					redirectAttributes.addFlashAttribute("source", ticket.getBus().getRoute().getStartName());
					redirectAttributes.addFlashAttribute("dest", ticket.getBus().getRoute().getStopName());
					redirectAttributes.addFlashAttribute("date", ticket.getDateOfTravel());
					redirectAttributes.addFlashAttribute("validCancel", "YES");
					ticketService.deleteByID(pnrNo);
				} else {
					redirectAttributes.addFlashAttribute("validCancel", "NO");
				}
			}

			ModelAndView modelAndView = new ModelAndView("redirect:/user/myTickets");
			return modelAndView;
		}

	}

	private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
		return PaytmChecksum.generateSignature(parameters, paytmDetails.getMerchantKey());
	}

}
