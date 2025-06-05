package com.ecommerceproject.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class User {

	@GeneratedValue(strategy=GenerationType.AUTO)
	private long Id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	
	
	public User() {
		
	}
	

	public User(long id, String username, String password, String email) {
		super();
		Id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
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
	
	
	
	
	
	
}
