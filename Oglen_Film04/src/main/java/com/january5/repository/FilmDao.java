package com.january5.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.january5.entity.Film;

public class FilmDao implements ICrud<Film> {

	@Override
	public void create(Film entity) {
		Session session = null;
		try {
			session = dataBaseConnectionHibernate();
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
			System.out.println("Film data is added to DB");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Film to DB");
		} finally {
			session.close();
		}

	}

	@Override
	public void delete(long id) {
		Film deletedFilm = find(id);
		Session session = null;

		try {
			String filmName = deletedFilm.getName();
			System.out.println(filmName);

			if (deletedFilm != null) {
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.delete(deletedFilm);
				session.getTransaction().commit();
				System.out.println("Film data is deleted to DB");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while deleted Film to DB");
		} finally {

			session.close();
		}

	}

	@Override
	public void update(long id, Film entity) {
		Session session = null;
		try {
			Film film = find(id);

			if (film != null) {
				film.setName(entity.getName());
				film.setOyuncu(entity.getOyuncu());
				film.setProduction_date(entity.getProduction_date());
				film.setYonetmen(entity.getYonetmen());
				film.setCategory(entity.getCategory());

				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.update(film);
				session.getTransaction().commit();
				System.out.println("Film data is added to DB");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Film to DB");
		} finally {
			session.close();
		}

	}

	@Override
	public List<Film> listAll() {
		Session session = null;
		session = dataBaseConnectionHibernate();
		String query = "select admin from Film as admin";
		TypedQuery<Film> typedQuery = session.createQuery(query, Film.class);
		List<Film> adminList = typedQuery.getResultList();
		adminList.forEach(System.out::println);
		return adminList;
	}

	@Override
	public Film find(long id) {
		Session session = dataBaseConnectionHibernate();
		Film film = null;
		try {
			film = session.find(Film.class, id);
			if (film != null) {
				System.out.println("Film Found--> " + film);
				return film;
			} else {
				System.out.println("Film not found");
				return null;
			}
		} catch (Exception e) {
			System.out.println("Some problem occured while finding Film to DB");
		} finally {
			session.close();
		}
		return null;
	}

}
