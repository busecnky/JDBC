package com.january5.repository;

import java.util.List;

import org.hibernate.Session;

import com.january5.util.HibernateUtils;

public interface ICrud<T> {
	public void create(T entity);

	public void delete(long id);

	public void update(long id, T entity);

	public List<T> listAll();

	public T find(long id);

	default Session dataBaseConnectionHibernate() {

		return HibernateUtils.getSessionFactory().openSession();
	}
}
