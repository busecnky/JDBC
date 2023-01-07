package com.january5.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.january5.entity.Odul;

public class OdulDao implements ICrud<Odul> {

	@Override
	public void create(Odul entity) {
		Session session = null;
		try {
			session = dataBaseConnectionHibernate();
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
			System.out.println("Odul data is added to DB");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Odul to DB");
		} finally {
			session.close();
		}

	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			Odul deletedOdul = find(id);
			if (deletedOdul != null) {
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.delete(deletedOdul);
				session.getTransaction().commit();
				System.out.println("Odul data is added to DB");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Odul to DB");
		} finally {
			session.close();
		}

	}

	@Override
	public void update(long id, Odul entity) {
		Session session = null;
		try {
			Odul odul = find(id);

			if (odul != null) {
				odul.setName(entity.getName());
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.update(odul);
				session.getTransaction().commit();
				System.out.println("Odul data is added to DB");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Odul to DB");
		} finally {
			session.close();
		}

	}

	@Override
	public List<Odul> listAll() {
		Session session = null;
		session = dataBaseConnectionHibernate();
		String query = "select odul from Odul as odul";
		TypedQuery<Odul> typedQuery = session.createQuery(query, Odul.class);
		List<Odul> adminList = typedQuery.getResultList();
		adminList.forEach(System.out::println);
		return adminList;
	}

	@Override
	public Odul find(long id) {
		Session session = dataBaseConnectionHibernate();
		Odul odul;
		try {
			odul = session.find(Odul.class, id);
			if (odul != null) {
				System.out.println("Odul Found--> " + odul);
				return odul;
			} else {
				System.out.println("Odul not found");
				return null;
			}
		} catch (Exception e) {
			System.out.println("Some problem occured while adding Odul to DB");
		} finally {
			session.close();
		}
		return null;
	}

}
