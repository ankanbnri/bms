package com.nrifintech.bms.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nrifintech.bms.entity.User;
import com.nrifintech.bms.repository.UserRepository;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	public UserRepository userRepo;

	
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
	public ModelAndView doLogin(HttpServletRequest request) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("-------------");
		User user = userRepo.findByEmail(email);
		System.out.println(user.toString());
		ModelAndView mv = new ModelAndView("searchBus");
		return mv;
	}
}