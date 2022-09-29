package com.revature.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Locations;

@Repository
public interface LocationRepository extends JpaRepository<Locations, Integer>{

	Optional<Locations> findById(Integer id);
	
}