package com.december27.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.december27.entity.Kitap;
import com.december27.utils.HibernateUtil;

public class KitapDao {

	public void save(Kitap kitap) throws Exception {
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start transaction
			transaction = session.beginTransaction();
			// save object
			session.save(kitap);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new Exception("kayıt başarısız!");
		}

	}

	public void deleteById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction ts = session.beginTransaction();
		String hql = "delete from Kitap kitap where id = " + id;
		Query query = session.createQuery(hql);
		query.executeUpdate();
		ts.commit();
	}
}
