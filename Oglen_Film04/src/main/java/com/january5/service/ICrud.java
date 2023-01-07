package com.january5.service;

import java.util.List;

import org.hibernate.Session;

import com.january5.util.HibernateUtils;

public interface ICrud<T> {
	
	public T create(T entity);
	
	public void delete(long id);
	
	public void update(long id, T entity);
	
	public List<T> listAll();
	
	public T find(long id);

}
