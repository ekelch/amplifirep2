package com.revature.controller;

import java.util.List;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Users;
import com.revature.service.UserService;
import com.revature.util.UserNotFoundException;

@RestController
public class UserController {

	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/api/v1/users/")
	public List<Users> getUsers(){
		return userService.getUsers();
	}
	
	@PostMapping("/api/v1/users/login/")
	public Users login(@RequestBody Users user) throws UserNotFoundException {
		return userService.login(user);
		
	}
	
	@GetMapping("/api/v1/users/{id}")
	public Users getUser(@PathVariable("id") Integer id) throws UserNotFoundException {
		return userService.getUser(id);
	}
	
}
