package com.december30.service;

import java.util.List;

import com.december30.entity.BookDetail;
import com.december30.repository.BookDetailDao;

public class BookDetailService implements IService<BookDetail> {

private BookDetailDao bookDetailDao;
	
	public BookDetailService() {
		this.bookDetailDao = new BookDetailDao();
	}
	
	@Override
	public void create(BookDetail entity) {
		bookDetailDao.create(entity);
	}

	@Override
	public void delete(long id) {
		bookDetailDao.delete(id);
	}

	@Override
	public void update(long id, BookDetail entity) {
		bookDetailDao.update(id, entity);
	}

	@Override
	public List<BookDetail> listAll() {
		return bookDetailDao.listAll();
	}

	@Override
	public BookDetail find(long id) {
		BookDetail bookDetail = bookDetailDao.find(id);
		return bookDetail;
	}
	
}
