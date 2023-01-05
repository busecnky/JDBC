package com.december30.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.december30.entity.BookDetail;



public class BookDetailDao implements IRepository<BookDetail>{

	@Override
	public void create(BookDetail entity) {
		Session session = null;
		
		try {
			session = dataBaseConnectionHibernate();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("BookDetail data is added to DB");
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding BookDetail to DB");
		}finally {
			session.close();
		}
			
		
		
	}

	@Override
	public void delete(long id) {
		Session session = null;
		
		try {
			BookDetail deleteBookDetail = find(id);
			if(deleteBookDetail != null) {
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(deleteBookDetail);
				session.getTransaction().commit();
				System.out.println("BookDetail data is added to DB");
			}
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding BookDetail to DB");
		}finally {
			session.close();
		}
		
	}

	@Override
	public void update(long id, BookDetail entity) {
		Session session = null;
		
		try {
			BookDetail bookDetail = find(id);
			if(bookDetail != null) {
				
				bookDetail.setBook(entity.getBook());
				bookDetail.setTitle(entity.getTitle());
				bookDetail.setBookBorrowedDate(entity.getBookBorrowedDate());
				bookDetail.setBookReturnedDate(entity.getBookReturnedDate());
				bookDetail.setBorrowed(entity.isBorrowed());
				
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.update(bookDetail);
				session.getTransaction().commit();
				System.out.println("BookDetail data is added to DB");
			}
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding BookDetail to DB");
		}finally {
			session.close();
		}
		
	}

	@Override
	public List<BookDetail> listAll() {
		Session session = null;
		session = dataBaseConnectionHibernate();
		String query = "select bookDetail from BookDetail as bookDetail";
		TypedQuery<BookDetail> typedQuery = session.createQuery (query, BookDetail.class);
		List<BookDetail> bookDetailList = typedQuery.getResultList();
		bookDetailList.forEach(System.out::println);
		return bookDetailList;
	}

	@Override
	public BookDetail find(long id) {
		Session session = dataBaseConnectionHibernate();
		BookDetail bookDetail;
		try {
			bookDetail = session.find(BookDetail.class, id);
			if(bookDetail!= null) {
				System.out.println("BookDetail found --> " + bookDetail);
				return bookDetail;
			}else {
				System.out.println("BookDetail not found");
				return null;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding BookDetail to DB");
		}
		return null;
	}




}
