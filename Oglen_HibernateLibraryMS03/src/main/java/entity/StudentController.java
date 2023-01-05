package entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.december30.entity.Book;
import com.december30.entity.Student;
import com.december30.service.BookService;
import com.december30.service.StudentService;
import com.december30.util.BAUtils;

public class StudentController {

	
	StudentService studentService;
	BookService bookService;

	public StudentController() {
		this.studentService = new StudentService();
		this.bookService = new BookService();
	}
	
	public void create() {
		String username = BAUtils.readString("Lütfen kullanıcı için belirlediğiniz kullanıcı adını giriniz: ");
		String password = BAUtils.readString("Lütfen kullanıcı için belirlediğiniz kullanıcı şifresini giriniz: ");
		
		Student student = new Student(username, password);
		
		studentService.create(student);
	}

	public void delete() {
		long id = BAUtils.readInt("Lütfen silme istediğiniz öğrencinin ID sini giriniz: ");
		studentService.delete(id);
		
	}

	public Optional<Student> findByUserName(String username){
		return studentService.findByUserName(username);
		
		
		
	}

	public void returnBook(Student student) {
		List<Book> books = student.getBooks();
		books.forEach(book -> System.out.println(book.getId() + " " + book.getDetail().getTitle()));
		int bookId = BAUtils.readInt("Iade etmek istediğiniz kitabın id'sini giriniz.");
		Book returnBook = bookService.find(bookId);
		returnBook.getDetail().setBorrowed(false);
		returnBook.getDetail().setBookReturnedDate(LocalDate.now());
		student.getBooks().remove(returnBook);
		bookService.update(bookId, returnBook);
		
		
	}


}
