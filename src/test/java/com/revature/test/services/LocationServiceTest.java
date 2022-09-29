package com.revature.test.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.willDoNothing;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.model.Locations;
import com.revature.repository.LocationRepository;
import com.revature.service.LocationService;
import com.revature.util.LocationNotFoundException;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

	@Mock private LocationRepository locationRepository;
	
	private LocationService underTest;
	
	@BeforeEach
	void setup() {
		underTest = new LocationService(locationRepository);
	}
	
	Locations location = new Locations(
			1,
			"The Cirque",
			"38054-81058"
			);
	
	@Test
	void canGetListOfLocations() {
		//when
		underTest.getLocations();
		//then
		verify(locationRepository,times(1)).findAll();
	}
	
	@Test
	void canGetLocationById() throws LocationNotFoundException {
		//given
		given(locationRepository.findById(1)).willReturn(Optional.of(location));
		//when
		Locations expected = underTest.getLocation(location.getId());
		//then
		assertThat(expected).isNotNull();
		verify(locationRepository, times(1)).findById(1);
	}
	
	@Test
	void canUpdateLocationByIdAndReturnUpdatedLocation() throws LocationNotFoundException {
		// given
		given(locationRepository.findById(1)).willReturn(Optional.of(location));
		given(locationRepository.save(location)).willReturn(location);
		location.setLocationName("The Cirque");
		// when
		underTest.updateLocation(location.getId(), location);
		// then
		ArgumentCaptor<Locations> userArgumentCaptor = ArgumentCaptor.forClass(Locations.class);
		verify(locationRepository).save(userArgumentCaptor.capture());
		
		Locations capturedLocation = userArgumentCaptor.getValue();
		assertThat(capturedLocation).isEqualTo(location);
	}
	
	@Test
	void canDeleteLocationById() {
		//when
		underTest.deleteLocation(location.getId());
		//then
		verify(locationRepository, times(1)).deleteById(location.getId());
	}
}
