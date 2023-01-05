package com.december29.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.december29.entity.Course;
import com.december29.entity.CourseMaterial;
import com.december29.entity.Student;
import com.december29.entity.Teacher;

public class HibernateUtils {

	
	private static final SessionFactory SESSION_FACTORY = sessionFactoryHibernate();

	private static SessionFactory sessionFactoryHibernate() {

		try {
			Configuration configuration = new Configuration();

			configuration.addAnnotatedClass(Teacher.class);
			configuration.addAnnotatedClass(Course.class);
			configuration.addAnnotatedClass(Student.class);
			configuration.addAnnotatedClass(CourseMaterial.class);

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
