package com.january5.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "film_oyuncu", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "oyuncu_id"))
	private Set<Oyuncu> oyuncu = new HashSet<>();

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "yonetmen_id", nullable = false)
	private Yonetmen yonetmen;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "odul_id")
	private Odul odul;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Category> category = new ArrayList<>();

	private LocalDate production_date;

	public Film(long id, String name, Set<Oyuncu> oyuncu, Yonetmen yonetmen, List<Odul> odul, List<Category> category,
			LocalDate production_date) {
		super();
		this.id = id;
		this.name = name;
		this.oyuncu = oyuncu;
		this.yonetmen = yonetmen;
		this.category = category;
		this.production_date = production_date;
	}

	public Film() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getProduction_date() {
		return production_date;
	}

	public void setProduction_date(LocalDate production_date) {
		this.production_date = production_date;
	}

	public Set<Oyuncu> getOyuncu() {
		return oyuncu;
	}

	public void setOyuncu(Set<Oyuncu> oyuncu) {
		this.oyuncu = oyuncu;
	}

	public Yonetmen getYonetmen() {
		return yonetmen;
	}

	public void setYonetmen(Yonetmen yonetmen) {
		this.yonetmen = yonetmen;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	public Odul getOdul() {
		return odul;
	}

	public void setOdul(Odul odul) {
		this.odul = odul;
	}
	

}
