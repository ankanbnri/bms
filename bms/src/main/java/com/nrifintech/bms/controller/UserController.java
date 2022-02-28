package com.nrifintech.bms.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nrifintech.bms.entity.User;
import com.nrifintech.bms.repository.UserRepository;
import com.nrifintech.bms.request.UserLoginRequest;
import com.nrifintech.bms.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	public UserRepository userRepo;
	
	@Autowired
	public UserService userService;

	@GetMapping("/searchBus")
	public ModelAndView searchBus() {
		ModelAndView mv = new ModelAndView("searchBus");
		return mv;
	}
	
	@GetMapping("/welcome")
	public String welcomeUser() {
		return "welcome";
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("UserAuth");
		return mv;
	}
	
//	@PostMapping("/login")
//	public ModelAndView doLogin(HttpServletRequest request) {
//		String email = request.getParameter("email");
//		String password = request.getParameter("password");
//		System.out.println("-------------");
//		User user = userRepo.findByEmail(email);
//		System.out.println(user.toString());
//		ModelAndView mv = new ModelAndView("searchBus");
//		return mv;
//	}
	
	@PostMapping("/login")
	public ModelAndView doLogin(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = userService.FindUser(email);
		System.out.println("---" + user + "---");
		
		if (user == null) {
			System.out.println("NO USER FOUND");
			ModelAndView uauth = new ModelAndView("UserAuth");
			uauth.addObject("error_msg", "No user found. Please Register.");
			uauth.addObject("email", email);
			return uauth;
		}
		else {
			boolean isValidUser = userService.isValidUser(user, password);
			if(isValidUser) {
				System.out.println("CORRECT USER");
				ModelAndView mv = new ModelAndView("redirect:/user/searchBus");
				return mv;
			}
			else {
				System.out.println("INVALID PASSWORD");
				ModelAndView uauth = new ModelAndView("UserAuth");
				uauth.addObject("error_msg", "Invalid Password. Please try again");
				uauth.addObject("email", email);
				return uauth;
			}
		}
		
		
	}
	
	
}