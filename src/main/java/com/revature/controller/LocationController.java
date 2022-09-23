package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Locations;
import com.revature.service.LocationService;
import com.revature.util.LocationNotFoundException;

@RestController
public class LocationController {

	private final LocationService locationService;
	
	@Autowired
	public LocationController(LocationService locationService) {
		this.locationService = locationService;
	}
	
	//Get a list of locations
	@GetMapping("/api/v1/locations")
	public List<Locations> getLocations(){
		return locationService.getLocations();
	}
	
	//Get a location by ID
	@GetMapping("/api/v1/locations/{id}")
	public Locations getLocation(@PathVariable("id") Integer id) throws LocationNotFoundException {
		return locationService.getLocation(id);
	}

	//Update location information (user can edit their profile)
	@PutMapping("/api/v1/locations/{id}")
	public void updateLocation(@PathVariable("id") Integer id, @RequestBody Locations location) throws LocationNotFoundException {
		locationService.updateLocation(id, location);
	}
	
	//Delete a location (for admin only)
	@DeleteMapping("/api/v1/locations/{id}")
	public void deleteLocation(@PathVariable("id") Integer id) throws LocationNotFoundException {
		locationService.deleteLocation(id);
	}
	
	//Location Registration
	@PostMapping("/api/v1/locations")
	public Locations register(@RequestBody Locations location) {
		return locationService.registerLocation(location);
	}
}