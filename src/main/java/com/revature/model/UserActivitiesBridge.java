package com.revature.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userid")
	private Integer userId;
	
	//@OneToMany(cascade = CascadeType.ALL)
	@Column(name="routeid")
	private Integer routeId;
	
	@Column(name="rating")
	private Integer rating;

}
