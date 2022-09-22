package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Routes;
import com.revature.model.Users;
import com.revature.service.RouteService;
import com.revature.util.RouteNotFoundException;

@RestController
//@RequestMapping("/api/v1/routes")
//@CrossOrigin("*")
public class RouteController {
	
	private RouteService routeService;

	@Autowired
	public RouteController(RouteService routeService) {
		super();
		this.routeService = routeService;
	}
	
	@GetMapping("/api/v1/routes/{id}")
	public Routes getRoutes(@PathVariable("id") Integer id) throws RouteNotFoundException{
		return routeService.getRoutes(id);
	}
	
	//Route Registration
		@PostMapping("/api/v1/routes")
		public Routes register(@RequestBody Routes route) {
			return routeService.register(route);
		}
	

}
