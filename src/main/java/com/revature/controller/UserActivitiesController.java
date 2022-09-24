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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.UserActivitiesBridge;
import com.revature.service.UserActivitiesService;
import com.revature.util.ActivityNotFoundException;

@RestController
@CrossOrigin("*")
public class UserActivitiesController {

	private final UserActivitiesService userActivitiesService;
	
	@Autowired
	public UserActivitiesController(UserActivitiesService userActivitiesService) {
		this.userActivitiesService = userActivitiesService;
	}
	
	//Add an activity
	@PostMapping("/api/v1/useractivities")
	public UserActivitiesBridge addActivity(@RequestBody UserActivitiesBridge ua) {
		return userActivitiesService.addActivity(ua);
	}
	
	@GetMapping("/api/v1/useractivities/{id}")
	public List<UserActivitiesBridge> getListOfActivitiesById(@PathVariable("id") Integer id) throws ActivityNotFoundException {
		return userActivitiesService.getListOfActivitiesById(id);
	}
	
//	@GetMapping("/api/v1/useractivities/{id}/{rating}")
//	public List<UserActivitiesBridge> getListOfActivitiesByIdAndRating(@PathVariable("id") Integer id, @PathVariable("rating") Integer rating) throws ActivityNotFoundException {
//		return userActivitiesService.getListOfActivitiesByIdAndRating(id, rating);
//	}
	
	
	//Still needs fixing
	@GetMapping("/api/v1/useractivities/rating/{rating}")
	public List<UserActivitiesBridge> getListOfActivitiesByRating(@PathVariable("rating") Integer rating) throws ActivityNotFoundException {
		return userActivitiesService.getListOfActivitiesByRating(rating);
	}
	

//	@PutMapping("/api/v1/useractivities/rating/{id}")
//	public void updateUserActivityRating(@PathVariable("id") Integer id, @PathVariable Integer rating) throws ActivityNotFoundException {
//		userActivitiesService.updateUserActivityRating(id, rating);
	//}
//	
//	//To be continued, must add extra parameters
//	//Update an activity by status
//	@PutMapping("/api/v1/useractivities/status/{id}")
//	public void updateUserActivityStatus(@PathVariable("id") Integer id, @RequestBody UserActivitiesBridge u) throws ActivityNotFoundException {
//		userActivitiesService.updateUserActivityStatus(id, u);
//	}
//	
//	//Delete a to-do/tick
//	@DeleteMapping("/api/v1/useractivities/{id}")
//	public void deleteActivity(@PathVariable("id") Integer id) throws ActivityNotFoundException {
//		 userActivitiesService.deleteActivity(id);
//	}
}
