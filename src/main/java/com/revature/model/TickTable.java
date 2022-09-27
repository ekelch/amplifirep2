package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tick_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TickTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tickId;
	
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="route_id")
	private Integer routeId;
	
	@Column(name="rating")
	private Integer rating;
}
