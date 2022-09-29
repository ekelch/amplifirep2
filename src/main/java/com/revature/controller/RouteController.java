package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Routes;
import com.revature.service.RouteService;
import com.revature.util.LocationNotFoundException;
import com.revature.util.RouteNotFoundException;
@CrossOrigin("http://127.0.0.1:5500/")

@RestController
public class RouteController {
	
	private RouteService routeService;

	@Autowired
	public RouteController(RouteService routeService) { 
		this.routeService = routeService;
	}
	
	//Get route by id
	@GetMapping("/api/v1/routes/{id}")
	public Routes getRoutes(@PathVariable("id") Integer id) throws RouteNotFoundException{
		return routeService.getRoutes(id);
	}
	
	//Get all routes
	@GetMapping("/api/v1/routes")
	public List<Routes> getRoutes(){
		return routeService.getListOfRoutes();
	}
	
	//Get all routes at location
	@GetMapping("/api/v1/routesByLocationId/{id}")
	public List<Routes> getRoutesByLocationId(@PathVariable("id") Integer id) throws LocationNotFoundException {
		return routeService.getRoutesByLocationId(id);
	}
	
	//Route Registration
	@PostMapping("/api/v1/routes/{locationId}")
	public Routes register(@RequestBody Routes route, @PathVariable("locationId") Integer locationId) throws RouteNotFoundException, LocationNotFoundException {
		return routeService.register(route, locationId);
	}
	
	//Delete a route
	@DeleteMapping("/api/v1/routes/{id}")
	public void deleteRoute(@PathVariable("id") Integer id) throws RouteNotFoundException {
		 routeService.deleteRoute(id);
	}
	
	//Update a route
	@PutMapping("/api/v1/routes/{id}")
	public void updateRoute(@PathVariable("id") Integer id, @RequestBody Routes route) throws RouteNotFoundException {
		routeService.updateRoute(id, route);
	}

}