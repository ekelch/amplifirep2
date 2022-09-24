package com.revature.service;

import java.util.List;

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
	

//	public List<UserActivitiesBridge> getListOfActivitiesByIdAndRating(Integer id, Integer rating) {
//		return userActivitiesRepo.findByIdAndRating(id, rating);
//	}
//	
	
	//Get a list of routes by rating
 	public List<UserActivitiesBridge> getListOfActivitiesByRating(Integer rating) throws ActivityNotFoundException{
 		return userActivitiesRepo.findByRating(rating, Sort.by("rating"));
 	}

	//Update an activity by rating
	//public void updateUserActivityRating(Integer id, Integer rating) throws ActivityNotFoundException {
			
	//	updateRating.setRating(updateRating.getRating());
			
	//	userActivitiesRepo.save(updateRating);		
//	}
//
//	//Update an activity by status
//	public void updateUserActivityStatus(Integer id, UserActivitiesBridge u) throws ActivityNotFoundException {
//		UserActivitiesBridge updateStatus = this.getListOfActivitiesById(id);
//		
//		updateStatus.setStatus(updateStatus.getStatus());
//		
//		userActivitiesRepo.save(updateStatus);
//	}
	
	//Delete a to-do/tick
	public void deleteActivity(Integer id) {
		userActivitiesRepo.deleteById(id);
		
	}


}
