package com.december26.repository;

import java.util.List;

import org.hibernate.Session;

import com.december26.util.HibernateUtils;

public interface ICrud<T> {

		void save(T t);
		
		void update(T t, long id);
		
		void delete(long id);
		
		List<T> findAll();
		
		T findById(long id);
		
		
		default Session dataBaseConnectionHibernate() {
			
			return HibernateUtils.getSessionFactory().openSession();
		}
		
}
