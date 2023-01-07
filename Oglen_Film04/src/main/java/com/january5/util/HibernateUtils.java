package com.january5.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.january5.entity.Category;
import com.january5.entity.Film;
import com.january5.entity.Odul;
import com.january5.entity.Oyuncu;
import com.january5.entity.Yonetmen;

public class HibernateUtils {
	private static final SessionFactory SESSION_FACTORY = sessionFactoryHibernate();

	private static SessionFactory sessionFactoryHibernate() {

		try {
			Configuration configuration = new Configuration();

			configuration.addAnnotatedClass(Category.class);
			configuration.addAnnotatedClass(Film.class);
			configuration.addAnnotatedClass(Odul.class);
			configuration.addAnnotatedClass(Oyuncu.class);
			configuration.addAnnotatedClass(Yonetmen.class);

			SessionFactory factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();

			return factory;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}
}
