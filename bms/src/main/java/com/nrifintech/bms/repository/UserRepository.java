package com.nrifintech.bms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nrifintech.bms.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	User findByEmailAndPassword(String email, String password);
	User findOneByEmail(String email);
}
