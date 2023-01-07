package com.january5.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.january5.entity.Oyuncu;

public class OyuncuDao implements ICrud<Oyuncu> {

	@Override
	public void create(Oyuncu entity) {
		Session session = null;
		try {
			session = dataBaseConnectionHibernate();
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
			System.out.println("Oyuncu data is added to DB");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Oyuncu to DB");
		} finally {
			session.close();
		}

	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			Oyuncu deletedOyuncu = find(id);
			if (deletedOyuncu != null) {
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.delete(deletedOyuncu);
				session.getTransaction().commit();
				System.out.println("Oyuncu data is added to DB");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Oyuncu to DB");
		} finally {
			session.close();
		}

	}

	@Override
	public void update(long id, Oyuncu entity) {
		Session session = null;
		try {
			Oyuncu oyuncu = find(id);

			if (oyuncu != null) {
				oyuncu.setName(entity.getName());
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.update(oyuncu);
				session.getTransaction().commit();
				System.out.println("Oyuncu data is added to DB");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Oyuncu to DB");
		} finally {
			session.close();
		}

	}

	@Override
	public List<Oyuncu> listAll() {
		Session session = null;
		session = dataBaseConnectionHibernate();
		String query = "select oyuncu from Oyuncu as oyuncu";
		TypedQuery<Oyuncu> typedQuery = session.createQuery(query, Oyuncu.class);
		List<Oyuncu> oyuncuList = typedQuery.getResultList();
		oyuncuList.forEach(System.out::println);
		return oyuncuList;
	}

	@Override
	public Oyuncu find(long id) {
		Session session = dataBaseConnectionHibernate();
		Oyuncu oyuncu;
		try {
			oyuncu = session.find(Oyuncu.class, id);
			if (oyuncu != null) {
				System.out.println("Oyuncu Found--> " + oyuncu);
				return oyuncu;
			} else {
				System.out.println("Oyuncu not found");
				return null;
			}
		} catch (Exception e) {
			System.out.println("Some problem occured while adding Oyuncu to DB");
		} finally {
			session.close();
		}
		return null;
	}

}
