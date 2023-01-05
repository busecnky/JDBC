package com.december26.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.december26.entity.Post;
import com.december26.util.HibernateUtils;

public class PostRepository implements ICrud<Post> {

	// openTransaction
	// successClose
	// errorClose


	private Session session;
	private Transaction transaction;
	private CriteriaBuilder criteriaBuilder;
	private EntityManager entityManager;

	public PostRepository() {
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
	public void save(Post t) {
		try {
			openTransaction();
			session.save(t);
			successClose();

		} catch (Exception e) {
			// TODO: handle exception
			errorClose();
		}

	}

	@Override
	public void update(Post t, long id) {
		try {
			openTransaction();
			t.setId(id);
			session.update(t);
			successClose();

		} catch (Exception e) {
			// TODO: handle exception
			errorClose();

		}

	}

	@Override
	public void delete(long id) {
		Post post = findById(id);
		if (post != null) {
			try {
				openTransaction();
				session.delete(post);
				successClose();
			} catch (Exception e) {
				errorClose();
			}
		} else {
			System.out.println(id + " li kullanic bulunmamaktadar");
		}
	}

	@Override
	public List<Post> findAll() {
		openTransaction();
		criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);
		Root<Post> root = criteriaQuery.from(Post.class);
		criteriaQuery.select(root);
		List<Post> userList = entityManager.createQuery(criteriaQuery).getResultList();
		for (Post post : userList) {
			System.out.println(post);
		}
		return userList;
	}

	@Override
	public Post findById(long id) {
		// CriteriaQuery--> Araştıralım
		openTransaction();
		criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);
		Root<Post> root = criteriaQuery.from(Post.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));

		return entityManager.createQuery(criteriaQuery).getSingleResult();
	}

}
