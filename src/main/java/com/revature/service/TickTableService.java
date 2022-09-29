package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.revature.model.TickTable;
import com.revature.repository.TickTableRepository;

@Service
public class TickTableService {

	private final TickTableRepository tickTableRepository;
	
	@Autowired
	public TickTableService(TickTableRepository tickTableRepository) {
		this.tickTableRepository = tickTableRepository;
	}
	
	public TickTable addActivity(TickTable tickTable) {
		return tickTableRepository.save(tickTable);
	}

	public List<TickTable> getListByUserId(Integer userId) {
		List<TickTable> tickId = tickTableRepository.findAll();
		List<TickTable> listByUserId = new ArrayList<TickTable>(); 
		for (TickTable tt:tickId) {
			if (tt.getUserId().equals(userId))
				listByUserId.add(tt);
		}
		return listByUserId;
	}

	public List<TickTable> getListByUserIdAndRouteId(Integer userId, Integer routeId) {
		List<TickTable> tickId = tickTableRepository.findAll();
		List<TickTable> listByUserIdandRouteId = new ArrayList<TickTable>(); 
		for (TickTable tt:tickId) {
			if (tt.getUserId().equals(userId) && tt.getRouteId().equals(routeId))
				listByUserIdandRouteId.add(tt);
		}
		return listByUserIdandRouteId;
	}

	public List<TickTable> getListByRating(Integer rating) {
		List<TickTable> tickId = tickTableRepository.findAll();
		List<TickTable> listByRating = new ArrayList<TickTable>(); 
		for (TickTable tt:tickId) {
			if (tt.getRating().equals(rating))
				listByRating.add(tt);
		}
		return listByRating;
	}

	public List<TickTable> updateActivity(Integer userId, Integer routeId, TickTable tickTable) {
		List<TickTable> oneAct= this.getListByUserIdAndRouteId(userId, routeId);
		for (TickTable tt: oneAct) {
			tt.setRating(tickTable.getRating());
		}
		return tickTableRepository.saveAll(oneAct);
	}

	public void deleteActivity(Integer userId, Integer routeId) {
		List<TickTable> oneAct= this.getListByUserIdAndRouteId(userId, routeId);
		tickTableRepository.deleteAll(oneAct);
	}
}

