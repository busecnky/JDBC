package com.december30.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.december30.entity.Author;
import com.december30.entity.Book;
import com.december30.entity.BookDetail;
import com.december30.entity.Student;
import com.december30.service.AuthorService;
import com.december30.service.BookDetailService;
import com.december30.service.BookService;
import com.december30.util.BAUtils;

public class BookController {
	
	BookDetailService bookDetailService;
	AuthorService authorService;
	BookService bookService;
	BookDetail bookDetail;
	
	
	public BookController() {
		this.bookDetailService = new BookDetailService();
		this.authorService = new AuthorService();
		this.bookService = new BookService();
		this.bookDetail = new BookDetail();
	}
	
	
	
	public void createBook() {
		Author author;
		Book book = new Book();
		String title = BAUtils.readString("Lütfen eklemek istediğiniz kitabın ismini girin");
		BookDetail bookDetail = new BookDetail();
		bookDetail.setTitle(title);
		bookDetailService.create(bookDetail);

		String firstName = BAUtils.readString("Lütfen yazarın ismini giriniz");
		String lastName = BAUtils.readString("Lütfen yazarın soyadını giriniz");

		author = authorService.findByName(firstName, lastName);
		if (author == null) {
			Author author2 = new Author(firstName, lastName);
			authorService.create(author2);
			book.setAuthor(author2);
		} else {
			book.setAuthor(author);
		}
		book.setDetail(bookDetail);
		bookService.create(book);

		// eğer yazar db varsa yeni kayıt atmicak yazarlar tablosun
		// yoksa hem yazar oluşturcak hemde kitap oluşturcak

	}
/*
	public void createBook2() {
		Author author;
		Book book = new Book();
		String title = BAUtils.readString("Lütfen eklemek istediğiniz kitabın ismini girin");
		BookDetail bookDetail = new BookDetail();
		bookDetail.setTitle(title);
		bookDetailService.create(bookDetail);
		String firstName = BAUtils.readString("Lütfen yazarın ismini giriniz");
		String lastName = BAUtils.readString("Lütfen yazarın soyadını giriniz");
		Optional<Author> optionalAuthor = authorService.findByName3(firstName, lastName);
		if (optionalAuthor.isEmpty()) {
			author = new Author(firstName, lastName);
			
			authorService.create(author);
			System.out.println(optionalAuthor.get().getFirstName()); 
		} else {
			author = optionalAuthor.get();
			System.out.println("yazar db de mevcut");
		}
		book.setAuthor(author);
		book.setDetail(bookDetail);
		bookService.create(book);
		// eğer yazar db varsa yeni kayıt atmicak yazarlar tablosun
		// yoksa hem yazar oluşturcak hemde kitap oluşturcak
*/



	public void borrowBook(Student student) {
		//ödünç alınmamış kitapları gösterelim
		// hangi kitaba ulaşmak istiyorsak id siyle kitabı bulmamız lazım
		//kaç gün kiralayacaksınız

		//kaç günlüğüne diye kullanıcıya sormamız lazım
		//student a kiraladığı kitapları ekleyeceğiz
		
		
		List<Book> books = bookService.listAll().stream().filter(bookdetail -> !bookdetail.getDetail().isBorrowed())
							.collect(Collectors.toList());
		
		books.forEach(book -> System.out.println(book.getId() + " " + book.getDetail().getTitle()));

		int bookId = BAUtils.readInt("Kiralamak istediğiniz kitabın ID'sini giriniz.");
		int duration = BAUtils.readInt("Kaç gün kiralamak istiyorsanız giriniz."); 
		
		Book borrowBook = bookService.find(bookId);
		LocalDate date = LocalDate.now();
		borrowBook.getDetail().setBookBorrowedDate(date);
		borrowBook.getDetail().setBookReturnedDate(date.plusDays(duration));
		borrowBook.getDetail().setBorrowed(true);
		borrowBook.getStudentList().add(student);
		student.getBooks().add(borrowBook);
		bookService.update(bookId, borrowBook);
		
	}
	
	public void delete() {
		long id = BAUtils.readInt("Lütfen silme istediginiz kitabın ID sini giriniz:");
		bookService.delete(id);
	}
	
	public void listAll() {
		bookService.listAll();
	}



	public void listBorrowedBooks() {
		bookService.listAll().stream().filter(book -> book.getDetail().isBorrowed() == true);
		
		//.forEach(book -> System.out.println(book));
	}



	public LocalDate returnDate() {
		int bookId = BAUtils.readInt ("Teslim tarihini bulmak istediginiz kitaban idsini giriniz") ;
		LocalDate date = bookService.find(bookId) .getDetail().getBookReturnedDate();
		System.out.println(date);
		return date;
		
	}
	
	public void returnBook(Student student) {
		List<Book> books = student.getBooks();
		books.forEach(book-> System.out.println(book.getId()+ " "+ book.getDetail().getTitle()));
		int bookId = BAUtils.readInt("Iade etmek istediginiz kitabın id'sini giriniz.");
		Book returnBook = bookService.find(bookId);
		returnBook.getDetail().setBorrowed(false);
		returnBook.getDetail().setBookReturnedDate(LocalDate.now());
		student.getBooks().remove(returnBook);
		bookService.update(bookId, returnBook);
	}
	
}





