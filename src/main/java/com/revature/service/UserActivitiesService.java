package com.revature.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
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
	
	//Get list by id
	public UserActivitiesBridge getActivityById(Integer id) throws ActivityNotFoundException {
		Optional<UserActivitiesBridge> ua = userActivitiesRepo.findById(id);
		
		if(ua.isPresent()) {
			return ua.get();
		}else {
			throw new ActivityNotFoundException("Activity not found");
		}
	}
	
	//Get list of user activities
	public List<UserActivitiesBridge> getListOfActivities() {
		return userActivitiesRepo.findAll();
	}
	
	//Get a list of routes by rating
	public List<UserActivitiesBridge> getListOfActivitiesByRating(Integer rating, Sort sort) throws ActivityNotFoundException{
		return userActivitiesRepo.findByRating(rating);
	}
	
	//Update an activity by rating
	public void updateUserActivityRating(Integer id, UserActivitiesBridge u) throws ActivityNotFoundException {
		UserActivitiesBridge updateRating = this.getActivityById(id);
		
		updateRating.setRating(updateRating.getRating());
		
		userActivitiesRepo.save(updateRating);		
	}

	//Update an activity by status
	public void updateUserActivityStatus(Integer id, UserActivitiesBridge u) throws ActivityNotFoundException {
		UserActivitiesBridge updateStatus = this.getActivityById(id);
		
		updateStatus.setStatus(updateStatus.getStatus());
		
		userActivitiesRepo.save(updateStatus);
	}
	
	//Delete a to-do/tick
	public void deleteActivity(Integer id) {
		userActivitiesRepo.deleteById(id);
		
	}


	
	
}
