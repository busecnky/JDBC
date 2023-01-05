package com.december26.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.december26.entity.Post;
import com.december26.entity.User;
import com.december26.entity.UserDetail;

public class HibernateUtils {

	
	
	private static final SessionFactory SESSION_FACTORY = sessionFactoryHibernate();

	
	private static SessionFactory sessionFactoryHibernate() {
		

			try {
			Configuration configuration = new Configuration();
			
		//üzerinde çalıştığımız classları gelip buraya ekliyoruz
			configuration.addAnnotatedClass(User.class);
			configuration.addAnnotatedClass(Post.class);
			configuration.addAnnotatedClass(UserDetail.class);   //Gömülü olanları Embedable eklemiyoruz.
			
			
			
			
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
