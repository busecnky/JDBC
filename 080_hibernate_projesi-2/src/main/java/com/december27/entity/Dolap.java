package com.december27.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
//@Table buraya sen tablo adını değiştirmeyeceksen class adına okeysen @table yazmana gerek yok
public class Dolap {

	@Id
	@GeneratedValue
	private int id;
	
	private String dolapno;

	
	public Dolap() {

	}
	
	
	public Dolap(String dolapno) {
		super();
		this.dolapno = dolapno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDolapno() {
		return dolapno;
	}

	public void setDolapno(String dolapno) {
		this.dolapno = dolapno;
	}


	@Override
	public String toString() {
		return "Dolap [id=" + id + ", dolapno=" + dolapno + "]";
	}
	
	
	
}
