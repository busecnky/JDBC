package com.january5.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Film {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private Yonetmen yonetmen;
	private Odul odul;
	private Category category;
	private LocalDate production_date;
	
	
	public Film(String name, Yonetmen yonetmen, Odul odul, Category category, LocalDate production_date) {
		super();
		this.name = name;
		this.yonetmen = yonetmen;
		this.odul = odul;
		this.category = category;
		this.production_date = production_date;
	}


	public Film() {
		super();
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


	public Yonetmen getYonetmen() {
		return yonetmen;
	}


	public void setYonetmen(Yonetmen yonetmen) {
		this.yonetmen = yonetmen;
	}


	public Odul getOdul() {
		return odul;
	}


	public void setOdul(Odul odul) {
		this.odul = odul;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public LocalDate getProduction_date() {
		return production_date;
	}


	public void setProduction_date(LocalDate production_date) {
		this.production_date = production_date;
	}


	@Override
	public String toString() {
		return "Film [id=" + id + ", name=" + name + ", yonetmen=" + yonetmen + ", odul=" + odul + ", category="
				+ category + ", production_date=" + production_date + "]";
	}
	
	
	
	
	
}
