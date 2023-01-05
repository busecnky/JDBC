package com.december27.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.december27.entity.Yazar;
import com.december27.utils.HibernateUtil;

public class YazarDao {

	public List<Yazar> getAll() {

		Transaction transaction = null;
		List<Yazar> list = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start transaction
			transaction = session.beginTransaction();
			// get Ogretmen objects
			list = session.createQuery("select yazar from Yazar yazar").list();
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return list;

	}

	
	
}
