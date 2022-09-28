package com.revature.service;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<Routes> getRoutesByLocationId(Integer locationId) {
		List<Routes> routes = routeRepository.findAll();
		List<Routes> locationRoutes = new ArrayList<Routes>(); // I do not know what type of list this should be.. -Evan
		for (Routes route:routes) {
			if (route.getLocation_id().getId().equals(locationId))
				locationRoutes.add(route);
		}
		return locationRoutes;
	}
	
	public List <Routes> getRoutes(){
		return routeRepository.findAll();
	}

	public Routes register(Routes route) {
		return routeRepository.saveAndFlush(route);
	}
	
	public void deleteRoute(Integer id) throws RouteNotFoundException {
		routeRepository.deleteById(id);
	}
	
	public void updateRoute(Integer id, Routes route) throws RouteNotFoundException {
		Routes updateRoute = this.getRoutes(id);
		
		updateRoute.setDifficulty(route.getDifficulty());
		updateRoute.setLength(route.getLength());
		updateRoute.setName(route.getName());
		updateRoute.setPhoto_url(route.getPhoto_url());
		
		routeRepository.save(updateRoute);
	}

}