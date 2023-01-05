package com.december30.entity;


import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public class User {

	
	private String username;
	private String password;
	
	
	
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
	
	public User() {
		super();
	}
	
	
	
	
}
