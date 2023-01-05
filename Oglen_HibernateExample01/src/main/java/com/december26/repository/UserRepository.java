package com.december26.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.december26.entity.User;
import com.december26.util.HibernateUtils;

public class UserRepository implements ICrud<User> {

	// openTransaction
	// successClose
	// errorClose

	// **************SORU***************
	private String name;
	private String surname;

	// getter ları var setter ları yok diyelim nasıl set ederiz bunları?
	/*
	 * Constructor da set edebiliriz. Metot yazıp metotta içine alıp set edebiliriz.
	 * public void setData(String name){ this.name = name; } gibi
	 */

	private Session session;
	private Transaction transaction;
	private CriteriaBuilder criteriaBuilder;
	private EntityManager entityManager;

	public UserRepository() {
		entityManager = HibernateUtils.getSessionFactory().createEntityManager();
		criteriaBuilder = entityManager.getCriteriaBuilder();
	}

	public void openTransaction() {
		session = dataBaseConnectionHibernate();
		transaction = session.beginTransaction();
	}

	public void successClose() {
		transaction.commit();
		session.close();
	}

	public void errorClose() {
		transaction.rollback();
		session.close();
	}

	@Override
	public void save(User t) {
		try {
			openTransaction();
			session.save(t);
			successClose();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void update(User t, long id) {
		try {
			openTransaction();
			t.setId(id);
			session.update(t);
			successClose();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void delete(long id) {
		User user = findById(id);
		if (user != null) {
			try {
				openTransaction();
				session.delete(user);
				successClose();
			} catch (Exception e) {
				errorClose();
			}
		} else {
			System.out.println(id + " li kullanic bulunmamaktadar");
		}
	}

	@Override
	public List<User> findAll() {
		openTransaction();
		criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		criteriaQuery.select(root);
		List<User> userList = entityManager.createQuery(criteriaQuery).getResultList();
		for (User user : userList) {
			System.out.println(user);
		}
		return userList;
	}

	@Override
	public User findById(long id) {
		// CriteriaQuery--> Araştıralım
		openTransaction();
		criteriaBuilder = session.getCriteriaBuilder();
		// Add restriction.
		openTransaction();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));

		return entityManager.createQuery(criteriaQuery).getSingleResult();
	}

	
	//startlike
	//sumPost
	
	//entity manager insert update delete
	public void startLike(String value) {
		// harf ile başlayan
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(criteriaBuilder.like(root.get("name").get("name"), value + "%"));
		List<User> users = entityManager.createQuery(query).getResultList();
		users.forEach(System.out::println);
		
		
	}
	
	public void sumPost(String value) {
		//HW
	}
}
