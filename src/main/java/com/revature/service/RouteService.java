package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Locations;
import com.revature.model.Routes;

import com.revature.repository.RouteRepository;
import com.revature.repository.LocationRepository;
import com.revature.util.LocationNotFoundException;
import com.revature.util.RouteNotFoundException;


@Service
public class RouteService {
	
	private LocationRepository locationRepository;
	private RouteRepository routeRepository;
	
	@Autowired
	public RouteService(RouteRepository routeRepository, LocationRepository locationRepository) {
		this.routeRepository = routeRepository;
		this.locationRepository = locationRepository;
	}
	
	public Routes getRoutes(Integer id) throws RouteNotFoundException{
		Optional<Routes> route = routeRepository.findById(id);
		
		if(route.isPresent()) {
			return route.get();
		}else {
			throw new RouteNotFoundException("Route not found");
		}
	}
	
	public List<Routes> getRoutesByLocationId(Integer id) {
		List<Routes> routes = routeRepository.findAll();
		List<Routes> locationRoutes = new ArrayList<Routes>(); 
		for (Routes route:routes) {
			if (route.getLocation().getId().equals(id))
				locationRoutes.add(route);
		}
		System.out.println(locationRoutes);
		return locationRoutes;
	}
	
	public List <Routes> getRoutes(){
		return routeRepository.findAll();
	}

	public Routes register(Routes route, Integer locationId) throws RouteNotFoundException, LocationNotFoundException {
		Optional<Routes> existingRoute = routeRepository.findByName(route.getName());
		if (existingRoute.isEmpty()) {
			Optional<Locations> location = locationRepository.findById(locationId);
			if (location.isPresent()) {
				route.setLocation(location.get());
			} else {
				throw new LocationNotFoundException("Not a valid location id");
			}
			Routes newRoute = routeRepository.saveAndFlush(route);
			return newRoute;
		} else {
			throw new RouteNotFoundException("Duplicate route entry");
		}
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