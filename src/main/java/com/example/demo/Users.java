package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Users {
	
	@Id
	Integer user_id;
	String username;
	String password;
	String email;
	String description;
	Integer zipcode;
	
	public Users(Integer user_id, String username, String password, String email, String description, Integer zipcode) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.description = description;
		this.zipcode = zipcode;
	}
	public Users(String username, String password, String email, String description, Integer zipcode) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.description = description;
		this.zipcode = zipcode;
	}
	public Users() {
		super();
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getZipcode() {
		return zipcode;
	}
	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}
	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", description=" + description + ", zipcode=" + zipcode + "]";
	}
	
	
	
}