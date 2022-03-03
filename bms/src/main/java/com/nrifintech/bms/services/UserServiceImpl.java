package com.nrifintech.bms.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nrifintech.bms.entity.User;
import com.nrifintech.bms.exception.RecordAlreadyExistsException;
import com.nrifintech.bms.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public ResponseEntity<?> createUser(User newUser){
		Optional<User> findUserById = userRepository.findById(newUser.getUserid());
		try {
			if(!findUserById.isPresent()) {
				userRepository.save(newUser);
				return new ResponseEntity<User>(newUser, HttpStatus.OK);
			} else
				throw new RecordAlreadyExistsException(
					"User with ID:" + newUser.getUserid() + "already exists!!");
		} catch (RecordAlreadyExistsException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
