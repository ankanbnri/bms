package com.nrifintech.bms.services;

import org.springframework.http.ResponseEntity;

import com.nrifintech.bms.entity.User;

public interface UserService {

	public ResponseEntity<?> createUser(User newUser);
}
