package com.december26.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.december26.entity.User;

public class UserDao implements ICrud<User> {

	@Override
	public void save(User t) {

		Transaction transaction = null;

		try (Session session = dataBaseConnectionHibernate()) {

			transaction = session.beginTransaction();
			session.save(t);
			transaction.commit();
			session.close();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();

			}
			System.out.println("Saved Error");
		}

	}

	@Override
	public void update(User t, long id) {
		Session session = null;
		try {
			User user = findById(id);
			if (user != null) {
				user.setUsername(t.getUsername());
//				user.setGender(t.getGender());
				user.setPassword(t.getPassword());
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(user);
				session.getTransaction().commit();
				System.out.println("Kullanıcı güncellendi");
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Override
	public void delete(long id) {

		Transaction transaction = null;

		try (Session session = dataBaseConnectionHibernate()) {
			User user = findById(id);
			if (user != null) {

				transaction = session.beginTransaction();
				session.delete(user);
				transaction.commit();
				System.out.println("User silindi---> " + user.getUsername());
			} else {
				System.out.println("ID ye ait kullanıcı bulunamadı");
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}

	@Override
	public List<User> findAll() {
		Transaction transaction = null;
		List<User> users = null;
		try (Session session = dataBaseConnectionHibernate()) {
			transaction = session.beginTransaction();
			users = session.createQuery("from User", User.class).list();
			transaction.commit();

			for (User user : users) {
				System.out.println(user);
			}
		}
		return users;
	}
	
	//TypedQuery ile yapılışı
	public List<User> findAll2() {
		Session session = null;
		List<User> userList = null;
		
		try {
			session = dataBaseConnectionHibernate();
			String query = "select users from User users";   //HQL den ötürü bu şekilde yazdık (Hibernate Query Language)
			TypedQuery<User> typedQuery = session.createQuery(query, User.class);
			userList = typedQuery.getResultList();

			for (User user : userList) {
				System.out.println(user);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return userList;
	}

	@Override
	public User findById(long id) {

		Session session = dataBaseConnectionHibernate();

		User user;

		try {
			user = session.find(User.class, id);
			if (user != null) {
				System.out.println("User bulundu -->" + user);
				return user;
			} else {
				System.out.println("Id yi kontrol edin");
			}
		} catch (Exception e) {
			System.out.println("Something wrong");
		} finally {
			session.close();
		}

		return null;
	}

}
