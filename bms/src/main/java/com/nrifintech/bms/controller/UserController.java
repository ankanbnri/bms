package com.nrifintech.bms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nrifintech.bms.entity.User;
import com.nrifintech.bms.service.UserService;
import com.nrifintech.bms.util.AdminPrivileges;
import com.nrifintech.bms.entity.Bus;
import com.nrifintech.bms.entity.Ticket;
import com.nrifintech.bms.service.BusService;
import com.nrifintech.bms.service.RouteService;
import com.nrifintech.bms.service.TicketService;



@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	public UserService userService;
	@Autowired
	private BusService busService;
	@Autowired
	private RouteService routeService;
	@Autowired
	private TicketService ticketService;
	
	@GetMapping("/welcome")
	public String welcomeUser() {
		return "welcome";
	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("UserAuth");
		return mv;
	}

	@PostMapping("/login")
	public ModelAndView doLogin(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		email = email.toLowerCase();
		String password = request.getParameter("password");
		User user = userService.findUser(email);

		if (user == null) {
			ModelAndView uauth = new ModelAndView("UserAuth");
			uauth.addObject("error_msg", "No user found. Please Register.");
			uauth.addObject("email", email);
			return uauth;
		} else {
			boolean isValidUser = userService.isValidUser(user, password);
			if (isValidUser) {
				HttpSession session = request.getSession();
				session.setAttribute("isValidUser", true);
				session.setAttribute("userid", user.getUserid());
				session.setAttribute("email", user.getEmail());
				session.setAttribute("name", user.getName());
				ModelAndView mv = new ModelAndView("redirect:/user/searchBus");
				return mv;
			} else {
				ModelAndView uauth = new ModelAndView("UserAuth");
				uauth.addObject("error_msg", "Invalid Password. Please try again");
				uauth.addObject("email", email);
				return uauth;
			}
		}

	}

	@GetMapping("/logout")
	public ModelAndView doLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("isValidUser");
		session.removeAttribute("userid");
		session.removeAttribute("email");
		session.removeAttribute("name");
		session.invalidate();
		ModelAndView mv = new ModelAndView("redirect:/user/welcome");
		return mv;
	}

	@GetMapping("/searchBus")
	public ModelAndView showSearchBusForm() {

		List<String> startNames = routeService.getDistinctRouteStartName();
		List<String> stopNames = routeService.getDistinctRouteStopName();
		
		ModelAndView modelAndView = new ModelAndView("searchBus");
		modelAndView.addObject("startNames", startNames);
		modelAndView.addObject("stopNames", stopNames);
		return modelAndView;
	}

	@PostMapping("/searchBus")
	public ModelAndView searchBus(@ModelAttribute("source") String source,
								  @ModelAttribute("destination") String destination,
								  @ModelAttribute("travelDate") String travelDate) throws ParseException {
		

		List<Bus> buses = busService.getBusWithSourceDest(source,destination);
		if(buses.size()>0) {
			busService.setAllAvailableSeatsForDate(buses,travelDate);
		}
		for(Bus bus: buses) {
			if(bus.getAvailableSeats()==0)
				buses.remove(bus);
		}

		ModelAndView modelAndView = new ModelAndView("listBus");
		if (buses.size() > 0) {
			modelAndView.addObject("buses", buses);
			modelAndView.addObject("travelDate", travelDate);
			modelAndView.addObject("busFound", true);
		}else {
			modelAndView.addObject("busFound", false);
		}
		return modelAndView;
	}
	
	@GetMapping("/myTickets")
	public ModelAndView showTickets(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userid")==null) {
			ModelAndView mv = new ModelAndView("redirect:/user/login");
			return mv;
		}else {
			int userId = (int)session.getAttribute("userid");
			User user = userService.getById(userId);
			
			List<Ticket> upcomingTickets = ticketService.getUpcomingTicketsWithUser(user);
			List<Ticket> oldTickets = ticketService.getOldTicketsWithUser(user);
			
			ModelAndView modelAndView = new ModelAndView("myTickets");
			
			// Setting upcoming tickets
			if(upcomingTickets.size()>0) {
				modelAndView.addObject("upcomingTickets", upcomingTickets);
				modelAndView.addObject("upcomingTicketFound", true);
			}else {
				modelAndView.addObject("upcomingTicketFound", false);
			}
			
			// Setting old tickets
			if(oldTickets.size()>0) {
				modelAndView.addObject("oldTickets", oldTickets);
				modelAndView.addObject("oldTicketFound", true);
			}else {
				modelAndView.addObject("oldTicketFound", false);
			}
			
			return modelAndView;
		}
	}


	@PostMapping("/signUp")
	public ModelAndView addUser(@ModelAttribute("user") User user) {
		user.setAdminPrivileges(AdminPrivileges.NO);
		User userExists = userService.findUser(user.getEmail());
		if (userExists != null) {
			System.out.println("userExists");
			ModelAndView uauth = new ModelAndView("UserAuth");
			uauth.addObject("error_msg", "Already registered user with this email.Please Login.");
			return uauth;
		}
		userExists = userService.findByMobileNo(user.getMobileNo());
		if (userExists != null) {
			ModelAndView uauth = new ModelAndView("UserAuth");
			uauth.addObject("error_msg", "Already registered user with this mobile number.Please Login.");
			return uauth;
		}
		ModelAndView modelAndView = new ModelAndView("redirect:/user/login");
		userService.save(user);
		return modelAndView;
	}
}