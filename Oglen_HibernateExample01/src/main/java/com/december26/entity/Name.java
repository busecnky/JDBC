package com.december26.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Name {

	
	private String name;
	private String middlename;
	private String lastname;
	
	
	public Name(String name, String middlename, String lastname) {
		super();
		this.name = name;
		this.middlename = middlename;
		this.lastname = lastname;
	}

	public Name() {
	
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getMiddlename() {
		return middlename;
	}


	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
}
