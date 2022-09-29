package com.revature.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Locations;
import com.revature.model.Routes;

@Repository
public interface RouteRepository extends JpaRepository<Routes, Integer>{

	Optional<Routes> findByName(String name);
	
}