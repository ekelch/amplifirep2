package com.revature.test.repository;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import com.revature.model.Users;
import com.revature.repository.UserRepository;

@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository underTest;
	
	@AfterEach
	void tearDown() {
		underTest.deleteAll();
	}
	
	
	@Test
	void itShouldFindUserByUsernameAndPassword() {
		// given
		Users user = new Users(
				1,
				"user1",
				"pass1",
				"user1@email.com",
				"i love sde",
				90778,
				"https://dl.dropboxusercontent.com/s/av08po3cupbll0g/chrome_Qtl2u3xBZy.jpg"
				);
		underTest.save(user);
		//when
		Users result=underTest.findByUsernameAndPassword("user1", "pass1").get();
		
		//then
		assertThat(result).isEqualTo(user);
	}
	
}
