package com.revature.test.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.model.TickTable;
import com.revature.repository.TickTableRepository;
import com.revature.service.TickTableService;

@ExtendWith(MockitoExtension.class)
public class TickTableServiceTest {

	@Mock private TickTableRepository tickTableRepository;
	
	private TickTableService underTest;
	
	@BeforeEach
	void setUp() {
		underTest = new TickTableService(tickTableRepository);
	}
	
	TickTable tickTable = new TickTable(
			1, 1, 1, 4);
	
	@Test
	void CanAddActivitiy() {
		//given
		underTest.addActivity(tickTable);
		// when
		ArgumentCaptor<TickTable> userArgumentCaptor = ArgumentCaptor.forClass(TickTable.class);
		verify(tickTableRepository).save(userArgumentCaptor.capture());
		//then
		TickTable capturedTick = userArgumentCaptor.getValue();
		assertThat(capturedTick).isEqualTo(tickTable);
		
	}
	
	@Test
	void CanGetListByUserId() {
		//given
		Integer userId = 1;
		underTest.getListByUserId(userId);
		//when
		verify(tickTableRepository, times(1)).findAll();
		//then
	}
	
	@Test
	void CanGetListByUserIdAndRouteId() {
		//given
		Integer userId = 1;
		Integer routeId = 1;
		//when
		underTest.getListByUserIdAndRouteId(userId, routeId);
		//then
		verify(tickTableRepository,times(1)).findAll();

	}
	
	@Test
	void CanGetListByRating() {
		//when
		underTest.getListByRating(4);
		//then
		verify(tickTableRepository,times(1)).findAll();
	}
	
	@Test
	void CanUpdateActivity() {
		//given
		Integer userId = 2;
		Integer routeId = 2;
		//when
		List<TickTable> result = underTest.updateActivity(userId, routeId, tickTable);
		//then
		verify(tickTableRepository,times(1)).findAll();
		verify(tickTableRepository,times(1)).saveAll(result);
	}
	
	@Test
	void CanDeleteActivity() {
		//given
		Integer userId =1;
		Integer routeId = 1;
		//when
		List<TickTable> result = underTest.getListByUserIdAndRouteId(userId, routeId);
		underTest.deleteActivity(userId, routeId);
		//then
		verify(tickTableRepository,times(1)).deleteAll(result);
	}
}

