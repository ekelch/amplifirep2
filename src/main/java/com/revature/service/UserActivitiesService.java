package com.revature.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.revature.model.UserActivitiesBridge;
import com.revature.repository.UserActivitiesRepository;
import com.revature.util.ActivityNotFoundException;

@Service
public class UserActivitiesService {

	private final UserActivitiesRepository userActivitiesRepo;
	
	@Autowired
	public UserActivitiesService(UserActivitiesRepository userActivitiesRepo) {
		this.userActivitiesRepo = userActivitiesRepo;
	
	}
	
	//Add an activity
	public UserActivitiesBridge addActivity(UserActivitiesBridge ua) {
		return userActivitiesRepo.save(ua);
	}
	
	//Get a list of activities by ID
	public List<UserActivitiesBridge> getListOfActivitiesById(Integer userId){
		return userActivitiesRepo.findAll();
	}
	
	//Get a list of activities by ID and Rating
	public List<UserActivitiesBridge> getListOfActivitiesByIdAndRating(Integer userId, Integer rating) {
		return userActivitiesRepo.findByIdAndRating(userId, rating);
	}
	
	//Get one activity by ID and Route ID - BUGGED - ONE more revision
	public UserActivitiesBridge getActivityByIdAndRouteId(Integer id, Integer routeId) throws ActivityNotFoundException {
		Optional<UserActivitiesBridge> userActivity = userActivitiesRepo.findByIdAndRouteId(id, routeId);
		
		if (userActivity.isPresent()) {
			
			UserActivitiesBridge userActivityActual = userActivity.get();
			return new UserActivitiesBridge(
					userActivityActual.getId(),
					userActivityActual.getRouteId(), 
					userActivityActual.getRating());
		} else {
			throw new ActivityNotFoundException("No Activity Found");
		}
	}
 	
	//Get a list of routes by rating
 	public List<UserActivitiesBridge> getListOfActivitiesByRating(Integer rating) throws ActivityNotFoundException{
 		return userActivitiesRepo.findByRating(rating, Sort.by("rating"));
 	}

	//Update an activity by User ID and Route ID
	public void updateUserActivityRating(Integer userId, Integer routeId, UserActivitiesBridge uActBridge) throws ActivityNotFoundException {
		UserActivitiesBridge updateAct = this.getActivityByIdAndRouteId(userId, routeId);
		
			updateAct.setRating(uActBridge.getRating());
			userActivitiesRepo.save(updateAct);
 	}
				
 	//Delete a to-do/tick
 	public void deleteActivity(Integer userId, Integer routeId) throws ActivityNotFoundException {
 		UserActivitiesBridge userActivity = this.getActivityByIdAndRouteId(userId, routeId);
 		userActivitiesRepo.delete(userActivity); 
 	}
}
