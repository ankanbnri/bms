package com.nrifintech.bms;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.nrifintech.bms.entity.User;
import com.nrifintech.bms.repository.UserRepository;
import com.nrifintech.bms.util.AdminPrivileges;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("sample1@sample.com");
		user.setUserid(77);
		user.setName("sample");
		user.setPassword("sample");
		user.setMobileNo("123456789");
		user.setAdminPrivileges(AdminPrivileges.NO);
		
		User savedUser = repo.save(user);
		
		User existUser = entityManager.find(User.class, savedUser.getUserid());
		
		//assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
	}
}
