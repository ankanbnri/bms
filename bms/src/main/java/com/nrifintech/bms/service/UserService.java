package com.nrifintech.bms.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.nrifintech.bms.entity.User;
import com.nrifintech.bms.repository.UserRepository;
import com.nrifintech.bms.util.AdminPrivileges;

@Service
public class UserService {
	@Autowired
	public UserRepository userRepo;

	public User findUser(String email) {
		User user = userRepo.findByEmail(email);
		if ((user != null) && (AdminPrivileges.NO.equals(user.getAdminPrivileges()))) {
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

	public User findAdmin(String email) {
		User user = userRepo.findByEmail(email);
		if ((user != null) && (AdminPrivileges.YES.equals(user.getAdminPrivileges()))) {
			return user;
		}
		return null;
	}

	public void save(User user) {

		String email = user.getEmail().toLowerCase();
		// System.out.println(email);
		user.setEmail(email);
		userRepo.save(user);
	}

}
