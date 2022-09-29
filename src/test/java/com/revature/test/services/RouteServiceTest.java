package com.revature.test.services;

import static org.assertj.core.api.Assertions.assertThat;



import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.model.Locations;
import com.revature.model.Routes;
import com.revature.repository.LocationRepository;
import com.revature.repository.RouteRepository;
import com.revature.service.RouteService;
import com.revature.util.LocationNotFoundException;
import com.revature.util.RouteNotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;


@ExtendWith(MockitoExtension.class)
public class RouteServiceTest {
	
	@Mock private RouteRepository routeRepository;
	@Mock private LocationRepository locationRepository;
	
	private RouteService underTest;
	
	@BeforeEach
	void setUp() {
	underTest = new RouteService(routeRepository, locationRepository);
	}
	
	Locations location = new Locations(
			1,
			"The Cirque",
			"38054-81058"
			);
	Routes route = new Routes(
			1, 
			"Open Mouths", 
			location, 
			"11a", 
			90, 
			"https://dl.dropboxusercontent.com/s/av08po3cupbll0g/chrome_Qtl2u3xBZy.jpg");
	
	@Test
	void canGetRoute() throws RouteNotFoundException {
		//given
		given(routeRepository.findById(1)).willReturn(Optional.of(route));
		//when
		Routes expected = underTest.getRoutes(route.getRoute_id());
		//then
		assertThat(expected).isNotNull();
		verify(routeRepository, times(1)).findById(1);
	}
	
	@Test
	void canGetRoutesByLocationId() {
		//when
		underTest.getRoutesByLocationId(location.getId());
		//then
		verify(routeRepository, times(1)).findAll();
	}
	
	@Test
	void canGetListOfRoutes() throws RouteNotFoundException {
		//when
		underTest.getListOfRoutes();
		//then
		verify(routeRepository, times(1)).findAll();
	}
	
	@Test
	void canRegisterRoute() throws RouteNotFoundException, LocationNotFoundException {
		// given
		given(routeRepository.findByName(route.getName())).willReturn(Optional.of(route));
		given(locationRepository.findById(location.getId())).willReturn(Optional.of(location));
		//when
		Routes expected = underTest.register(route, route.getRoute_id());
		assertThat(expected).isNotNull();
		//then
		verify(routeRepository,times(1)).findByName("Open Mouths");
		verify(locationRepository,times(1)).findById(1);
	}
	
	@Test
	void canDeleteRoute() throws RouteNotFoundException {
		//given
		underTest.deleteRoute(route.getRoute_id());
		//when
		verify(routeRepository,times(1)).deleteById(1);
		//then
		assertThat(route).isNull();
	}
	
	@Test
	void canUpdateRoute() throws RouteNotFoundException {
		//given
		given(routeRepository.save(route)).willReturn(route);
		route.setDifficulty("11b");
		route.setLength(99);
		route.setName("Closed Mouths");
		route.setPhoto_url("https://dl.dropboxusercontent.com/s/av08po3cupbll0g.jpg");
		//when
		underTest.updateRoute(route.getRoute_id(), route);
		//then
		ArgumentCaptor<Routes> routesArgumentCaptor = ArgumentCaptor.forClass(Routes.class);
		verify(routeRepository,times(1)).save(routesArgumentCaptor.capture());
		
		Routes capturedRoute = routesArgumentCaptor.getValue();
		assertThat(capturedRoute).isEqualTo(route);
	}
}
