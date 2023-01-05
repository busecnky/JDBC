package com.december27.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Sinif {

	
	@Id
	@GeneratedValue
	private int sinifID;
	private String lokasyon;
	
	@OneToOne(cascade = CascadeType.ALL)  //cascade all diyerek biz sınıfı kaydettiğimizde ayrıca lab ı da kaydetmemize gerek yok o kaydediyo
	private Lab lab;
	
	//OneToMany örneği için: Birden fazla dolap olsaydı bu şekilde yapardık
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Dolap> dolaplar;
	
	
	//@ManyToOne örneği: Bir sürü sınıf bir tane okulda olabilir
	@ManyToOne (cascade = CascadeType.ALL)
	private Okul okul;
	
	public Sinif() {
		
	}

	
	
	public Sinif(String lokasyon, Lab lab, List<Dolap> dolaplar, Okul okul) {
		super();
		this.lokasyon = lokasyon;
		this.lab = lab;
		this.dolaplar = dolaplar;
		this.okul = okul;
	}



	public Sinif(int sinifID, Lab lab, String lokasyon) {
		super();
		this.sinifID = sinifID;
		this.lab = lab;
		this.lokasyon = lokasyon;
	
	}

	public Sinif(Lab lab, String lokasyon) {
		super();
		this.lab = lab;
		this.lokasyon = lokasyon;
	}

	public int getSinifID() {
		return sinifID;
	}

	public void setSinifID(int sinifID) {
		this.sinifID = sinifID;
	}

	public String getLokasyon() {
		return lokasyon;
	}

	public void setLokasyon(String lokasyon) {
		this.lokasyon = lokasyon;
	}

	public Lab getLab() {
		return lab;
	}

	public void setLab(Lab lab) {
		this.lab = lab;
	}

	
	
	public List<Dolap> getDolaplar() {
		return dolaplar;
	}

	public void setDolaplar(List<Dolap> dolaplar) {
		this.dolaplar = dolaplar;
	}



	public Okul getOkul() {
		return okul;
	}



	public void setOkul(Okul okul) {
		this.okul = okul;
	}



	@Override
	public String toString() {
		return "Sinif [sinifID=" + sinifID + ", lokasyon=" + lokasyon + ", lab=" + lab + ", dolaplar=" + dolaplar + "]";
	}

	

	
	
	
}
