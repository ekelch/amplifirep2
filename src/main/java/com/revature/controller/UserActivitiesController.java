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
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@GetMapping("/api/v1/useractivities/{id}/{rating}")
	public List<UserActivitiesBridge> getListOfActivitiesByIdAndRating(@RequestParam("id") Integer id, @RequestParam("rating") Integer rating) throws ActivityNotFoundException {
		return userActivitiesService.getListOfActivitiesByIdAndRating(id, rating);
	}
	
	//Bugged
//	@PostMapping("/api/v1/useractivities/{id}/{routeId}")
//	public UserActivitiesBridge getActivityByIdAndRouteId(@RequestBody UserActivitiesBridge userAct) throws ActivityNotFoundException {
//		return userActivitiesService.getActivityByIdAndRouteId(userAct);
//	}
	
	//List of Activities by Rating
	@GetMapping("/api/v1/useractivities/rating/{rating}")
	@ResponseBody
	public List<UserActivitiesBridge> getListOfActivitiesByRating(@PathVariable("rating") Integer rating) throws ActivityNotFoundException {
		return userActivitiesService.getListOfActivitiesByRating(rating);
	}
	
	//Update an activity by User ID and Route ID
	@PutMapping("/api/v1/useractivities/rating/{id}/{routeId}")
	public void updateUserActivityRating(@PathVariable("Id") Integer id, @PathVariable("routeId") Integer routeId, @RequestBody UserActivitiesBridge uActBridge) throws ActivityNotFoundException {
		userActivitiesService.updateUserActivityRating(id, routeId, uActBridge);
	}

// 	//Delete a to-do/tick
// 	@DeleteMapping("/api/v1/useractivities/{id}/{routeId}")
// 	public void deleteActivity(@PathVariable("id") Integer id, @PathVariable("routeId") Integer routeId) throws ActivityNotFoundException {
// 		 userActivitiesService.deleteActivity(id, routeId);
// 	}
}
