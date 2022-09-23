package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.UserActivitiesBridge;

@Repository
public interface UserActivitiesRepository extends JpaRepository<UserActivitiesBridge, Integer>{

	List<UserActivitiesBridge> findByRating(Integer rating);
	



}