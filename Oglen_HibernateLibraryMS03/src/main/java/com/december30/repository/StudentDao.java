package com.december30.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.december30.entity.Student;


public class StudentDao implements IRepository<Student> {

	@Override
	public void create(Student entity) {
		Session session = null;
		try {
			session = dataBaseConnectionHibernate();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("Student data is added to DB");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Student to DB");
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			Student deletedStudent = find(id);
			if (deletedStudent != null) {
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(deletedStudent);
				session.getTransaction().commit();
				System.out.println("Student data is deleted from DB");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while deleting Student to DB");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(long id, Student entity) {
		Session session = null;
		try {
			Student student = find(id);
			
			if (student != null) {
				student.setUsername(entity.getUsername());
				student.setPassword(entity.getPassword());
				student.setBooks(entity.getBooks());
				
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.update(student);
				session.getTransaction().commit();
				System.out.println("Student data is updated");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while updating Student to DB");
		} finally {
			session.close();
		}

	}

	@Override
	public List<Student> listAll() {
		Session session = null;
		session = dataBaseConnectionHibernate();
		String query = "select student from Student as student";
		TypedQuery<Student> typedQuery = session.createQuery(query,Student.class);
		List<Student> studentList = typedQuery.getResultList();
		studentList.forEach(System.out::println);
		return studentList;
	}

	@Override
	public Student find(long id) {
		Session session = dataBaseConnectionHibernate();
		Student student;
		try {
			student = session.find(Student.class, id);
			if (student != null) {
				System.out.println("Student Found--> " + student);
				return student;
			} else {
				System.out.println("Student not found");
				return null;
			}
		} catch (Exception e) {
			System.out.println("Some problem occured while finding Student to DB");
		} finally {
			session.close();
		}
		return null;
	}
	
	
	public Optional<Student> findByUserName(String username) {
		Session session = dataBaseConnectionHibernate();
		Student student = null;
		String hql = "select user from Student as user where user.username = :key ";
		Query query = session.createQuery(hql);
		query.setParameter("key",username);

		try {
			student = (Student) query.getSingleResult();
			return Optional.of(student);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
	
}
