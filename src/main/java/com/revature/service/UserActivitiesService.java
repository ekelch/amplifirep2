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
	public List<UserActivitiesBridge> getListOfActivitiesById(Integer id){
		return userActivitiesRepo.findAll();
	}
	
	//Get a list of activities by ID and Rating
	public List<UserActivitiesBridge> getListOfActivitiesByIdAndRating(Integer id, Integer rating) {
		return userActivitiesRepo.findByIdAndRating(id, rating);
	}
	
	//Get one activity by ID and Route ID - BUGGED - ONE more revision
	public UserActivitiesBridge getActivityByIdAndRouteId(UserActivitiesBridge userAct) throws ActivityNotFoundException {
		Optional<UserActivitiesBridge> userActivity = userActivitiesRepo.findByIdAndRouteId(userAct.getId(), userAct.getRoute_id());
		
		if (userActivity.isPresent()) {
			
			UserActivitiesBridge userActivityActual = userActivity.get();
			return new UserActivitiesBridge(
					userActivityActual.getId(), 
					userActivityActual.getRoute_id(), 
					userActivityActual.getRating());
		} else {
			throw new ActivityNotFoundException("No Activity Found");
		}
	}
//	
	//Get a list of routes by rating
 	public List<UserActivitiesBridge> getListOfActivitiesByRating(Integer rating) throws ActivityNotFoundException{
 		return userActivitiesRepo.findByRating(rating, Sort.by("rating"));
 	}

	//Update an activity by User ID and Route ID
	public void updateUserActivityRating(Integer id, Integer routeId, UserActivitiesBridge uActBridge) throws ActivityNotFoundException {
		UserActivitiesBridge updateRating = this.getActivityByIdAndRouteId(uActBridge);
		updateRating.setRating(updateRating.getRating());
		userActivitiesRepo.save(updateRating);		
	}
	
//	//Delete a to-do/tick
//	public void deleteActivity(Integer id, Integer routeId) {
//		UserActivitiesBridge oneActivity = this.getActivityByIdAndRouteId(null)
//		userActivitiesRepo.deleteById(id);
//		
//	}


}
