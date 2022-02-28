package com.nrifintech.bms.repository;

import org.springframework.stereotype.Repository;

import com.nrifintech.bms.entity.User;

@Repository
public interface UserRepository extends AbstractBaseRepository<User, Integer> {
	User findByEmailAndPassword(String email, String password);
	User findByEmail(String email);
}
