package com.revature.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Routes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer route_id;
	
	private String name;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="location_id")
	private Locations location;
	private String difficulty;
	private Integer length;
	private String photo_url;
	

}