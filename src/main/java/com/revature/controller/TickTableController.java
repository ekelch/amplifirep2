package com.revature.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.TickTable;
import com.revature.service.TickTableService;

@RestController
public class TickTableController {

	private final TickTableService tickTableService;
	
	public TickTableController(TickTableService tickTableService) {
		this.tickTableService = tickTableService;
	}
	
	@PostMapping("/api/v1/activity")
	public TickTable addActivity(@RequestBody TickTable tickTable) {
		return tickTableService.addActivity(tickTable);
	}
	
	//Get List of Activity by user_id
	@GetMapping("/api/v1/activity/{userId}")
	public List<TickTable> getListByUserId(@PathVariable("userId") Integer userId){
		return tickTableService.getListByUserId(userId);
	}
	
	//Get activity by user_id and route_id
	@GetMapping("/api/v1/activity/{userId}/{routeId}")
	public List<TickTable> getListByUserIdAndRouteId(@PathVariable("userId") Integer userId, @PathVariable("routeId") Integer routeId){
		return tickTableService.getListByUserIdAndRouteId(userId, routeId);
	}
	
	//Get list of activities by rating
	@GetMapping("/api/v1/activity/rating/{rating}")
	public List<TickTable> getListByRating(@PathVariable("rating") Integer rating){
		return tickTableService.getListByRating(rating);
	}
	
	//Update an activity by user_id and route_id
	@PutMapping("/api/v1/activity/rating/{userId}/{routeId}")
	public List<TickTable> updateActivity(@PathVariable("userId") Integer userId, @PathVariable("routeId") Integer routeId, @RequestBody TickTable tickTable){
		return tickTableService.updateActivity(userId, routeId, tickTable);
	}
	
	//Delete an activity by user_id and route_id
	@DeleteMapping("/api/v1/activity/{userId}/{routeId}")
	public void deleteActivity(@PathVariable("userId") Integer userId, @PathVariable("routeId") Integer routeId){
		tickTableService.deleteActivity(userId, routeId);
	}
}