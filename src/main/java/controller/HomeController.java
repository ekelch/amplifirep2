package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import repository.entity.User;
import service.UserService;

@RestController
@RequestMapping("/response")
public class HomeController {
	
	private UserService userService;
	
	
	@Autowired
	public HomeController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping(
			value = "/postbody",
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> postBody(@RequestBody User user){
		User persistedUser = userService.save(user);
		return new ResponseEntity<>(persistedUser, HttpStatus.CREATED);
	}
}
