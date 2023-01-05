package com.december30.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.december30.entity.Book;



public class BookDao implements IRepository<Book> {

	@Override
	public void create(Book entity) {
		Session session = null;
		try {
			session = dataBaseConnectionHibernate();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("Book data is added to DB");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Book to DB");
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			Book deletedBook = find(id);
			if (deletedBook != null) {
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(deletedBook);
				session.getTransaction().commit();
				System.out.println("Book data is added to DB");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Book to DB");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(long id, Book entity) {
		Session session = null;
		try {
			Book book = find(id);
			
			if (book != null) {
				book.setAuthor(entity.getAuthor());
				book.setDetail(entity.getDetail());
				book.setStudentList(entity.getStudentList());
				
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				
				//update ve merge farkı!!! Bak
				session.merge(book);
				session.getTransaction().commit();
				System.out.println("Book data is added to DB");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Book to DB");
		} finally {
			session.close();
		}

	}

	@Override
	public List<Book> listAll() {
		Session session = null;
		session = dataBaseConnectionHibernate();
		String query = "select book from Book as book";
		TypedQuery<Book> typedQuery = session.createQuery(query, Book.class);
		List<Book> bookList = typedQuery.getResultList();
		bookList.forEach(System.out::println);
		return bookList;
	}

	@Override
	public Book find(long id) {
		Session session = dataBaseConnectionHibernate();
		Book book;
		try {
			book = session.find(Book.class, id);
			if (book != null) {
				System.out.println("Book Found--> " + book);
				return book;
			} else {
				System.out.println("Book not found");
				return null;
			}
		} catch (Exception e) {
			System.out.println("Some problem occured while adding Book to DB");
		} finally {
			session.close();
		}
		return null;
	}



}
