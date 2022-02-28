package com.nrifintech.bms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrifintech.bms.entity.User;
import com.nrifintech.bms.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	public UserRepository userRepo;
	
	public User FindUser(String email) {
		User user = userRepo.findByEmail(email);
		if (user != null) {
			return user;
		}
		return null;
	}
	
	public boolean isValidUser(User user, String password) {
		String userPassword = user.getPassword();
		if (userPassword.equals(password)) {
			return true;
		}
		return false;
	}

}
