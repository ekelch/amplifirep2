package com.revature.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.revature.model.UserActivitiesBridge;

@Repository
public interface UserActivitiesRepository extends JpaRepository<UserActivitiesBridge, Integer>{

	
	List<UserActivitiesBridge> findByRating(Integer rating, Sort sort);

	List<UserActivitiesBridge> findByIdAndRating(Integer userId, Integer rating);

	//Bugged
	Optional <UserActivitiesBridge> findByIdAndRouteId(Integer userId, Integer routeId);

}
