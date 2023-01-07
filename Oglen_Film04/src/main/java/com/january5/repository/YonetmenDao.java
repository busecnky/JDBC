package com.january5.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.january5.entity.Yonetmen;

public class YonetmenDao implements ICrud<Yonetmen> {

	@Override
	public void create(Yonetmen entity) {
		Session session = null;
		try {
			session = dataBaseConnectionHibernate();
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
			System.out.println("Yonetmen data is added to DB");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Yonetmen to DB");
		} finally {
			session.close();
		}

	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			Yonetmen deletedYonetmen = find(id);
			if (deletedYonetmen != null) {
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.delete(deletedYonetmen);
				session.getTransaction().commit();
				System.out.println("Yonetmen data is added to DB");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Yonetmen to DB");
		} finally {
			session.close();
		}

	}

	@Override
	public void update(long id, Yonetmen entity) {
		Session session = null;
		try {
			Yonetmen category = find(id);

			if (category != null) {
				category.setName(entity.getName());
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.update(category);
				session.getTransaction().commit();
				System.out.println("Yonetmen data is added to DB");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Yonetmen to DB");
		} finally {
			session.close();
		}

	}

	@Override
	public List<Yonetmen> listAll() {
		Session session = null;
		session = dataBaseConnectionHibernate();
		String query = "select yonetmen from Yonetmen as yonetmen";
		TypedQuery<Yonetmen> typedQuery = session.createQuery(query, Yonetmen.class);
		List<Yonetmen> yonetmenList = typedQuery.getResultList();
		yonetmenList.forEach(System.out::println);
		return yonetmenList;
	}

	@Override
	public Yonetmen find(long id) {
		Session session = dataBaseConnectionHibernate();
		Yonetmen yonetmen;
		try {
			yonetmen = session.find(Yonetmen.class, id);
			if (yonetmen != null) {
				System.out.println("Yonetmen Found--> " + yonetmen);
				return yonetmen;
			} else {
				System.out.println("Yonetmen not found");
				return null;
			}
		} catch (Exception e) {
			System.out.println("Some problem occured while adding Yonetmen to DB");
		} finally {
			session.close();
		}
		return null;
	}

}
