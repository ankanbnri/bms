package com.nrifintech.bms.repository;

import org.springframework.stereotype.Repository;

import com.nrifintech.bms.entity.User;

@Repository
public interface UserRepository extends AbstractBaseRepository<User, Long> {
	User findByUsernameAndPassword(String username, String password);
}
