package com.december30.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Entity
public class BookDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "title")
	private String title;
	
	
	@Column(name = "is_borrowed")
	private boolean isBorrowed;
	
	@Column(name = "borrowed_date")
	private LocalDate bookBorrowedDate;
	
	@Column(name = "return_date")
	private LocalDate bookReturnedDate;
	
	@OneToOne(mappedBy = "detail")
	private Book book;

	public BookDetail(long id, String title, boolean isBorrowed, LocalDate bookBorrowedDate, LocalDate bookReturnedDate,
			Book book) {
		super();
		this.id = id;
		this.title = title;
		this.isBorrowed = isBorrowed;
		this.bookBorrowedDate = bookBorrowedDate;
		this.bookReturnedDate = bookReturnedDate;
		this.book = book;
	}

	public BookDetail() {
		super();
	}

	@Override
	public String toString() {
		return "BookDetail [id=" + id + ", title=" + title + ", isBorrowed=" + isBorrowed + ", bookBorrowedDate="
				+ bookBorrowedDate + ", bookReturnedDate=" + bookReturnedDate + "]";
	}
	
	
	
	
}
