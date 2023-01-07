package com.january5;



import com.january5.controller.FilmController;
import com.january5.util.HibernateUtils;

public class Test {
	
	public static void main(String[] args) {
		
		//HibernateUtils.getSessionFactory().openSession();
		
		FilmController filmController = new FilmController();
		//filmController.createFilm();
		
		filmController.delete();
		
		//filmController.find();
	}
}
