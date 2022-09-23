package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
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
	
//	//To be continued
////	//Get list by id
////	@GetMapping("/api/v1/useractivities/{id}")
////	public UserActivitiesBridge getActivityById(@PathVariable("id") Integer id) throws ActivityNotFoundException{
////		return userActivitiesService.getActivityById(id);
//	}
	
	//Get list of user activities
	@GetMapping("/api/v1/useractivities")
	public List<UserActivitiesBridge> getListOfActivities(){
			return userActivitiesService.getListOfActivities();
	}
		
	//To be continued
//	//Get a list of activities by rating, must add extra parameters
//	@GetMapping("/api/v1/useractivities/rating")
//	public List<UserActivitiesBridge> getListOfActivitiesByRating(@RequestParam("rating") Integer rating, Sort sort) throws ActivityNotFoundException{
//			return userActivitiesService.getListOfActivitiesByRating(rating, sort);
//	}
	
	//To be continued
	//Update an activity by rating, must add extra parameters
	@PutMapping("/api/v1/useractivities/rating/{id}")
	public void updateUserActivityRating(@PathVariable("id") Integer id, @RequestBody UserActivitiesBridge u) throws ActivityNotFoundException {
		userActivitiesService.updateUserActivityRating(id, u);
	}
	
	//To be continued, must add extra parameters
	//Update an activity by status
	@PutMapping("/api/v1/useractivities/status/{id}")
	public void updateUserActivityStatus(@PathVariable("id") Integer id, @RequestBody UserActivitiesBridge u) throws ActivityNotFoundException {
		userActivitiesService.updateUserActivityStatus(id, u);
	}
	
	//To be continued, must add extra parameters
	//Delete a to-do/tick
	@DeleteMapping("/api/v1/useractivities/{id}")
	public void deleteActivity(@PathVariable("id") Integer id) throws ActivityNotFoundException {
		 userActivitiesService.deleteActivity(id);
	}
}
