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

import com.nrifintech.bms.entity.User;
import com.nrifintech.bms.service.UserService;
import com.nrifintech.bms.util.AdminPrivileges;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	public UserService userService;

	@GetMapping("/welcome")
	public String welcomeUser() {
		return "welcome";
	}

	@PostMapping("/signUp")
	public ModelAndView addUser(@ModelAttribute("user") User user) {
		user.setAdminPrivileges(AdminPrivileges.NO);
		User userExists = userService.findUser(user.getEmail());
		if (userExists != null) {
			System.out.println("userExists");
			ModelAndView uauth = new ModelAndView("UserAuth");
			uauth.addObject("error_msg", "Already registered user with this email");
			return uauth;
		}
		userExists = userService.findByMobileNo(user.getMobileNo());
		if (userExists != null) {
			ModelAndView uauth = new ModelAndView("UserAuth");
			uauth.addObject("error_msg", "Already registered user with this mobile number");
			return uauth;
		}
		ModelAndView modelAndView = new ModelAndView("redirect:/user/login");
		userService.save(user);
		return modelAndView;
	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("UserAuth");
		return modelAndView;
	}

}