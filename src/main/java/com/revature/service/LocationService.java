package com.revature.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Locations;

import com.revature.repository.LocationRepository;
import com.revature.util.LocationNotFoundException;

@Service
public class LocationService {

	private final LocationRepository locationRepository;
	
	@Autowired
	public LocationService(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}
	
	public List<Locations> getLocations() {
		return locationRepository.findAll();
	}

	public Locations getLocation(Integer id) throws LocationNotFoundException {
		Optional<Locations> location = locationRepository.findById(id);
			
			if(location.isPresent()) {
				return location.get();
			} else {
				throw new LocationNotFoundException("Location Not Found");
			}
	}

	public void updateLocation(Integer id, Locations location) throws LocationNotFoundException {
		Locations updateLocation = this.getLocation(id);
		
		updateLocation.setLocationName(location.getLocationName());
		
		locationRepository.save(updateLocation);
	}

	public void deleteLocation(Integer id) {
		locationRepository.deleteById(id);
		
	}

	public Locations registerLocation(Locations location) {
		return locationRepository.saveAndFlush(location);
	}
	
	
}