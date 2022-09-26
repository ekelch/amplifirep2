package com.revature.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.UserActivitiesBridge;
import com.revature.service.UserActivitiesService;
import com.revature.util.ActivityNotFoundException;

@RestController
//@CrossOrigin("*")
public class UserActivitiesController {

	private final UserActivitiesService userActivitiesService;
	
	@Autowired
	public UserActivitiesController(UserActivitiesService userActivitiesService) {
		this.userActivitiesService = userActivitiesService;
	}
	
	//Add an activity //ok-tested
	@PostMapping("/api/v1/useractivities")
	public UserActivitiesBridge addActivity(@RequestBody UserActivitiesBridge ua) {
		return userActivitiesService.addActivity(ua);
	}
	
	//ok-tested
	@GetMapping("/api/v1/useractivities/{userId}")
	public List<UserActivitiesBridge> getListOfActivitiesById(@PathVariable("userId") Integer userId) throws ActivityNotFoundException {
		return userActivitiesService.getListOfActivitiesById(userId);
	}
	
	//ok-tested
	@GetMapping("/api/v1/useractivities/{userId}/{rating}")
	public List<UserActivitiesBridge> getListOfActivitiesByIdAndRating(@PathVariable("userId") Integer userId, @PathVariable("rating") Integer rating) throws ActivityNotFoundException {
		return userActivitiesService.getListOfActivitiesByIdAndRating(userId, rating);
	}
	
	//ok-tested
	@GetMapping("/api/v1/useractivities/one/{userId}/{routeId}")
	public UserActivitiesBridge getActivityByIdAndRouteId(@PathVariable("userId") Integer userId, @PathVariable("routeId") Integer routeId) throws ActivityNotFoundException {
		return userActivitiesService.getActivityByUserIdAndRouteId(userId, routeId);
	}
	
	//ok-tested
	//List of Activities by Rating
	@GetMapping("/api/v1/useractivities/rating/{rating}")
	@ResponseBody
	public List<UserActivitiesBridge> getListOfActivitiesByRating(@PathVariable("rating") Integer rating) throws ActivityNotFoundException {
		return userActivitiesService.getListOfActivitiesByRating(rating);
	}
	
	//It works but only updates by User ID, not by Both
	//Update an activity by User ID and Route ID
	@PutMapping("/api/v1/useractivities/rating/{userId}/{routeId}")
	public void updateUserActivityRating(@PathVariable("userId") Integer userId, @PathVariable("routeId") Integer routeId, @RequestBody UserActivitiesBridge uActBridge) throws ActivityNotFoundException {
		userActivitiesService.updateUserActivityRating(userId, routeId, uActBridge);
	}
	
	//it works but only deletes by User ID, not by UserID and RouteIDd
  	//Delete a to-do/tick
  	@DeleteMapping("/api/v1/useractivities/{userId}/{routeId}")
  	public void deleteActivity(@PathVariable("userId") Integer id, @PathVariable("routeId") Integer routeId) throws ActivityNotFoundException {
  		 userActivitiesService.deleteActivity(id, routeId);
  	}
}
