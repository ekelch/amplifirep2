package com.revature.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Routes;
import com.revature.repository.RouteRepository;
import com.revature.util.RouteNotFoundException;

@Service
public class RouteService {
	
	
	private RouteRepository routeRepository;
	
	@Autowired
	public RouteService(RouteRepository routeRepository) {
		super();
		this.routeRepository = routeRepository;
	}
	
	public Routes getRoutes(Integer id) throws RouteNotFoundException{
		Optional<Routes> route = routeRepository.findById(id);
		
		if(route.isPresent()) {
			return route.get();
		}else {
			throw new RouteNotFoundException("Route not found");
		}
	}

}
