package com.nrifintech.bms.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.nrifintech.bms.dto.UserRequest;
import com.nrifintech.bms.entity.User;
import com.nrifintech.bms.repository.UserRepository;
import com.nrifintech.bms.service.UserService;
import com.nrifintech.bms.util.AdminPrivileges;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	public UserRepository userRepo;
	
	@Autowired
	public UserService userService;
	
	@GetMapping("/welcome")
	public String welcomeUser() {
		return "welcome";
	}
	
	/*
	@PostMapping("/signUp")
	public RedirectView addUser(User user, RedirectAttributes redir) {
		user.setAdminPrivileges(AdminPrivileges.NO);
		userService.save(user);
		RedirectView redirectView = new RedirectView("/user/login", true);
		redir.addFlashAttribute("message", "Successfully registered");
		return redirectView;
	}
	*/
	@PostMapping("/signUp")
	public ModelAndView addUser(@ModelAttribute("user") User user )
	{		
		user.setAdminPrivileges(AdminPrivileges.NO);
		User userExists = userRepo.findByEmail(user.getEmail());
		if(userExists != null) {
			System.out.println("userExists");
			ModelAndView uauth = new ModelAndView("UserAuth");
			//uauth.addObject("user");
			//uauth.addObject("old_user", "Already registered user with this email");
			uauth.addObject("error_msg", "Already registered user with this email");
			return uauth;
		}
		userExists = userRepo.findByMobileNo(user.getMobileNo());
		if(userExists != null) {
			//System.out.println("userExists");
			ModelAndView uauth = new ModelAndView("UserAuth");
			//uauth.addObject("user");
			//uauth.addObject("old_mobile", "Already registered user with this mobile number");
			uauth.addObject("error_msg", "Already registered user with this mobile number");
			return uauth;
		}
		ModelAndView modelAndView  = new ModelAndView("redirect:/user/login");
		//RedirectView redirectView = new RedirectView("/user/login", true);
		//redirectView.addStaticAttribute("message", "Successfully registered");
		//modelAndView.addObject(redirectView);
		userService.save(user);
		return modelAndView;
	}
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("UserAuth");
		return modelAndView;
	}
	
	
}