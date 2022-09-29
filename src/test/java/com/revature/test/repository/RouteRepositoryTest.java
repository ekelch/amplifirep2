package com.revature.test.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.revature.model.Locations;
import com.revature.model.Routes;
import com.revature.repository.RouteRepository;

@DataJpaTest
public class RouteRepositoryTest {

	@Autowired
	private RouteRepository underTest;
	
	@AfterEach
	void tearDown() {
		underTest.deleteAll();
	}
	
	@Test
	void canFindByName() {
		//given
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
		underTest.save(route);
		//when
		Routes routeSearch = underTest.findByName("Open Mouths").get();
		//then
		assertThat(routeSearch).isEqualTo(route);
	}
}
