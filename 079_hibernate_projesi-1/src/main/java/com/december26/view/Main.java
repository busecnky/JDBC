package com.december26.view;

import java.util.List;

import com.december26.entities.Ogretmen;
import com.december26.repository.OgretmenDao;

public class Main {

	public static OgretmenDao ogretmenDao;
	
	
	public Main() {		
		ogretmenDao = new OgretmenDao();
	}
	
	public void save() {
	
		Ogretmen ogretmen = new Ogretmen("Fahri", "Aslan", 32);
		
		
		try {
			ogretmenDao.saveOgretmen(ogretmen);			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Kayıt başarısız!");
		}
	}
	
	// Öğretmen kayıt güncelleme
	public void update() {
	
		//Ogretmen ogr = new Ogretmen("Ahmet", "Şahin", 25);
		//ogr.setId(1);	
		
		//ister yukarıdaki gibi kendin set id yap istersen aşağıdaki gibi constructorda belirt farketmiyor
		
		Ogretmen ogr = new Ogretmen(1, "Ahmet", "Çelik", 24, 1);
		
		try {
			ogretmenDao.update(ogr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// tüm öğretmenleri listeleme
	public void getAll() {
		List<Ogretmen> list = null;
		
		try {
			list = ogretmenDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(Ogretmen ogr : list) {
			System.out.println(ogr);
		}
		
	}
	
	// sıralı olarak ogretmenleri listeleme
	public void findWithOrder() {
		List<Ogretmen> list = null;
		
		try {
			list = ogretmenDao.findWithOrder();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(Ogretmen ogr : list) {
			System.out.println(ogr);
		}
	}
	
	
	// id'ye göre Ogretmeni bulma
	public void getById() {
		Ogretmen ogr = null;
		try {
			ogr = ogretmenDao.getById(5);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		if (ogr == null) {
			System.out.println("Kayıt bulunamadı!");
		}
		else
			System.out.println("\n" + ogr);
	}
	
	// id'ye göre Ogretmen kaydı silme
	public void deleteById() {
		try {
			ogretmenDao.deleteById(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// Ogretmen nesnesi göndererek delete işlemi
	public void delete() {
		Ogretmen ogr = new Ogretmen(6, "Fahri", "Aslan", 32, 5);
		try {
			ogretmenDao.delete(ogr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void main(String[] args) {
	
		Main main = new Main();
		
		
		
		main.save();
		

	}

}
