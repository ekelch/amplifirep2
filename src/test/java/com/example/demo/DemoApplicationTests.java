package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Stream;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.model.Users;
import com.revature.repository.UserRepository;
import com.revature.service.UserService;

@SpringBootTest(classes=UserService.class)
class DemoApplicationTests {

	
	@Autowired
	private UserService service;

	@MockBean
	private UserRepository repository;

	public void contextLoads() throws Exception{
		assertThat(service).isNotNull();
		assertThat(repository).isNotNull();
	}
	/*
	 * @Test
	 * 
	 * public void getUsersTest() { when(repository.findAll().thenReturn(Stream.of
	 * (new Users(1, "user1", "pass1", "user1@email.com", "user testing", 99988),
	 * new Users(2, "user2", "pass2", "user2@email.com", "user testing", 99987)
	 * ).Collectors.toList())); assertEquals(2, service.getUsers().size()); }
	 */

	@Test
	public void registerTest() {
		Users user = new Users(null, "user3", "pass3", "user3@email.com", "user testing", 99986, null);
		when(repository.save(user)).thenReturn(user);
		assertEquals(user, service.register(user));
	}

}
