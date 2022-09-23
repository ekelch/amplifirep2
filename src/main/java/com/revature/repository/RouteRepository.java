package com.revature.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Routes;

@Repository
public interface RouteRepository extends JpaRepository<Routes, Integer>{

}
