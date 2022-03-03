package com.nrifintech.bms.controller;


//import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nrifintech.bms.entity.User;
import com.nrifintech.bms.exception.RecordAlreadyExistsException;
import com.nrifintech.bms.repository.UserRepository;
import com.nrifintech.bms.services.UserService;


@Controller
@RequestMapping("/user")
@ComponentScan(basePackages = "com")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/welcome")
	public String welcomeUser() {
		return "welcome";
	}
	
	@PostMapping(value="/createUser")
	@ExceptionHandler(RecordAlreadyExistsException.class)
	public void addUser(@RequestBody User newUser) {
		userService.createUser(newUser);
	}
}