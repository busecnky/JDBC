package com.january5.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Odul {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@OneToMany(mappedBy = "odul", cascade = CascadeType.ALL)
	private List<Film> film = new ArrayList<>();
	
	@OneToMany(mappedBy = "odul", cascade = CascadeType.ALL)
	private List<Yonetmen> yonetmen =new ArrayList<>();

	public Odul(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Odul() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
