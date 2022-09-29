package com.revature.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controller.UserController;
import com.revature.model.Users;
import com.revature.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;
//	ObjectMapper objectMapper = new ObjectMapper();
//	
//	@Mock
//	private UserService userService;
//	
//	@InjectMocks
//	private UserController userController;
//	
//	private Users user1
//		= new Users(
//				1,
//				"user1",
//				"pass1",
//				"user1@email.com",
//				"i love sde",
//				90778,
//				"https://dl.dropboxusercontent.com/s/av08po3cupbll0g/chrome_Qtl2u3xBZy.jpg"
//				);
//	private Users user2
//		= new Users(
//				2,
//				"user2",
//				"pass2",
//				"user2@email.com",
//				"i love swe",
//				90779,
//				"https://dl.dropboxusercontent.com/s/av08po3cupbll0g/chrome_Qtl2u3xBZy.jpg"
//				);
//	private Users user3
//	= new Users(
//				3,
//				"user3",
//				"pass3",
//				"user3@email.com",
//				"i love cs",
//				90777,
//				"https://dl.dropboxusercontent.com/s/av08po3cupbll0g/chrome_Qtl2u3xBZy.jpg"
//				);
	
	@Test
	public void getUser() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/users/1")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect((ResultMatcher) content().string(equalTo(
		"{\"user_id\":1,\"username\":\"user1\",\"password\":\"pass1\",\"email\":\"user1@email.com\",\"description\":\"i love coffee\",\"zipcode\":90776,\"photo_url\":\"https://drive.google.com/file/d/1C1m9JTijQDVYJWVnsli1xeNAqpmhBuu6/view?usp=sharing\"}"
		)));
	}
	
//	@Test
//	public void login()throws Exception{
//		mvc.perform(MockMvcRequestBuilders.get("/api/v1/users/login")
//		.accept(MediaType.APPLICATION_JSON))
//		.andExpect(status().isOk())
//		.andExpect((ResultMatcher) content().string(equalTo(
//		"{\"user_id\":1,\"username\":\"user1\",\"password\":\"pass1\",\"email\":\"user1@email.com\",\"description\":\"i love coffee\",\"zipcode\":90776,\"photo_url\":\"https://drive.google.com/file/d/1C1m9JTijQDVYJWVnsli1xeNAqpmhBuu6/view?usp=sharing\"}"		
//		)));
//	}
//
//
//	
//	@Test
//	public void register() throws Exception{
//		
//		mvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
//		.contentType(MediaType.APPLICATION_JSON)
//		.content(objectMapper.writeValueAsString(user1))
//		.accept(MediaType.APPLICATION_JSON))
//		.andExpect(status().isOk());
//	}
	
}
