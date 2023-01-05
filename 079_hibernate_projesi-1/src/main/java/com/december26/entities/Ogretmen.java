package com.december26.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;


//Bunlara anotasyon denir. Yardımcı kütüphanelerle bağlantısını sağlamak için bunları yazıyoruz.
@Entity
@Table //(name = "teacher")    böyle yazarsak tabloya isim veririz yoksa sınıf adı ne ise onunla aynı olur
public class Ogretmen {

	@Id     //Primary key olduğunu belirtmen için Id anotasyonunu kullanıyoruz.
	@GeneratedValue  //Primary keyi kendi verir, sen nesne içerisinde id versen de dikkate almaz. Serial
	private int id;
	
	
	@Column(nullable = true)   //Tablodaki ad sütunu null olabilir demek.
	private String ad;
	
	@Column(nullable = false, length = 20)   //not null olmasın diyoruz ayrıca 20 karakter uzunluğunda bir sütun olsun diyoruz
	private String soyad;
	
	
	@Column(columnDefinition = "int default 18", nullable = false)
	private int yas;   //default: not null
	
	
	
	@ColumnDefault(value = "5")	
	@Column(name = "hizmet_yili", nullable = false)  //Tablodaki sütun isminin class içindeki üye değişkeninden farklı olmasını
	private int hizmetYili;									//olmasını istiyorsanız name ile belirtin.

	
	
	//İLeride lazım olacağı için boş, hepsi olan ve id primary key olduğu için idsiz 3 adet constructor oluşturuyoruz.
	
	public Ogretmen() {
		super();
	}




	public Ogretmen(int id, String ad, String soyad, int yas, int hizmetYili) {
		super();
		this.id = id;
		this.ad = ad;
		this.soyad = soyad;
		this.yas = yas;
		this.hizmetYili = hizmetYili;
	}




	public Ogretmen(String ad, String soyad, int yas, int hizmetYili) {
		super();
		this.ad = ad;
		this.soyad = soyad;
		this.yas = yas;
		this.hizmetYili = hizmetYili;
	}

	public Ogretmen(String ad, String soyad, int yas) {
		super();
		this.ad = ad;
		this.soyad = soyad;	
		this.yas = yas;
		this.hizmetYili = 5;
	}


	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getAd() {
		return ad;
	}




	public void setAd(String ad) {
		this.ad = ad;
	}




	public String getSoyad() {
		return soyad;
	}




	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}




	public int getYas() {
		return yas;
	}




	public void setYas(int yas) {
		this.yas = yas;
	}




	public int getHizmetYili() {
		return hizmetYili;
	}




	public void setHizmetYili(int hizmetYili) {
		this.hizmetYili = hizmetYili;
	}




	@Override
	public String toString() {
		return "Ogretmen [id=" + id + ", ad=" + ad + ", soyad=" + soyad + ", yas=" + yas + ", hizmetYili=" + hizmetYili
				+ "]";
	}
	
	
	
	
	
	
	
}
