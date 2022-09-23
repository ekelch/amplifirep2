package com.revature.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user_activities_bridge")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserActivitiesBridge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer user_id;
	
	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name="route_id")
	Integer route_id;
	
	Integer rating;
	String status;
}
