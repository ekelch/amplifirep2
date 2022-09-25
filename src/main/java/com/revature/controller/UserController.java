package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Users;
import com.revature.service.UserService;
import com.revature.util.UserNotFoundException;
@CrossOrigin("http://127.0.0.1:5500/")

@RestController
public class UserController {

	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	//User Registration
	@PostMapping("/api/v1/users")
	public Users register(@RequestBody Users user) {
		return userService.register(user);
	}
	
	
	//Get a list of users (for admin use)
	@GetMapping("/api/v1/users")
	public List<Users> getUsers(){
		return userService.getUsers();
	}
	
	//User login authentication
	@PostMapping("/api/v1/users/login")
	public Users login(@RequestBody Users user) throws UserNotFoundException {
		return userService.login(user);
		
	}
	
	//Display user information (for home page)
	@GetMapping("/api/v1/users/{id}")
	public Users getUser(@PathVariable("id") Integer id) throws UserNotFoundException {
		return userService.getUser(id);
	}

	//Update user information (user can edit their profile)
	@PutMapping("/api/v1/users/{id}")
	public void updateUser(@PathVariable("id") Integer id, @RequestBody Users user) throws UserNotFoundException {
		userService.updateUser(id, user);
	}
	
	//Delete a user (for admin only)
	@DeleteMapping("/api/v1/users/{id}")
	public void deleteUser(@PathVariable("id") Integer id) throws UserNotFoundException {
		 userService.deleteUser(id);
	}
	
}
