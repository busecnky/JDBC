package com.january5.controller;

import java.time.LocalDate;

import com.january5.entity.Film;
import com.january5.entity.Category;
import com.january5.entity.Odul;
import com.january5.entity.Oyuncu;
import com.january5.entity.Yonetmen;
import com.january5.repository.CategoryDao;
import com.january5.repository.OdulDao;
import com.january5.repository.OyuncuDao;
import com.january5.repository.YonetmenDao;
import com.january5.service.FilmService;
import com.january5.util.BAUtils;

public class FilmController {
	
	private FilmService filmService;
	private OdulDao odulDao;
	private OyuncuDao oyuncuDao;
	private YonetmenDao yonetmenDao;
	private CategoryDao categoryDao;
	
	public FilmController() {
		this.filmService = new FilmService();
		this.odulDao = new OdulDao();
		this.oyuncuDao = new OyuncuDao();
		this.yonetmenDao = new YonetmenDao();
		this.categoryDao = new CategoryDao();
	}
	public void createFilm() {
		
		Film film = new Film();
		String FilmIsmi = BAUtils.readString("film ismini giriniz ");
		String YonetmenIsmi = BAUtils.readString("yonetmen ismini giriniz ");
		String OyuncuIsmi = BAUtils.readString("oyuncu ismini giriniz ");
		String OdulIsmi = BAUtils.readString("odul ismini giriniz ");
		String kategory = BAUtils.readString("kategory ismini giriniz ");
		
		Oyuncu oyuncu = new Oyuncu(OyuncuIsmi);
		oyuncuDao.create(oyuncu);

		Odul odul = new Odul();
		odul.setName(OdulIsmi);
		odulDao.create(odul);
		
		Yonetmen yonetmen = new Yonetmen();
		yonetmen.setName(YonetmenIsmi);
		yonetmen.setOdul(odul);

		yonetmenDao.create(yonetmen);
		
		Category category = new Category();
		category.setName(kategory);
		categoryDao.create(category);

		film.getCategory().add(category);
		film.setName(FilmIsmi);
		film.getOyuncu().add(oyuncu);
		film.setProduction_date(LocalDate.now());
		film.setYonetmen(yonetmen);
		film.setOdul(odul);
		filmService.create(film);
		
	}
	public void delete() {
		int id = BAUtils.readInt("Lütfen silme istediğiniz filmin ID sini giriniz: ");
		filmService.delete(id);
	}
	public void find() {
		long id = BAUtils.readInt("Liddddz: ");
		filmService.find(id);
	}
	
	//update delete, list
	
}
