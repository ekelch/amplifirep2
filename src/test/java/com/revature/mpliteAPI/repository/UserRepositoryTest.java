package com.revature.mpliteAPI.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import repository.UserRepository;
import repository.entity.User;

@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	void isUserExistsById() {
		User user = new User(1L, "user", "pass", "email", "desc", 44044);
		userRepository.save(user);
		
		Boolean actualResult = userRepository.isUserExistById(user.getId());
		
		assertThat(actualResult).isTrue();
	}
	
}
