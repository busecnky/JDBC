package com.december30.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.december30.entity.Book;
import com.december30.entity.Student;
import com.december30.service.BookService;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class StudentManager extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private BookService bookService;
	private JTextField txtid;
	private JTextField txtduration;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	
	private int bookId;
	public StudentManager(Optional<Student> student) {

		this.bookService = new BookService();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Odunc Al");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book borrowBook = bookService.find(bookId);
				int duration = Integer.parseInt(txtduration.getText());
				LocalDate date = LocalDate.now();
				borrowBook.getDetail().setBookBorrowedDate(date);
				borrowBook.getDetail().setBookReturnedDate(date.plusDays(duration));
				borrowBook.getDetail().setBorrowed(true);
				
				borrowBook.getStudentList().add(student.get());
				student.get().getBooks();
				bookService.update(bookId, borrowBook);
				getTable();
			}
		});
		btnNewButton.setBounds(10, 26, 85, 55);
		contentPane.add(btnNewButton);

		JButton btnIadeEt = new JButton("Iade Et");
		btnIadeEt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book returnBook = bookService.find(bookId);
				List<Book> books = student.get().getBooks();
				returnBook.getDetail().setBorrowed(false);
				returnBook.getDetail().setBookReturnedDate(LocalDate.now());
				student.get().getBooks().remove(returnBook);
				bookService.update(bookId, returnBook);
				getTable();
			}
		});
		btnIadeEt.setBounds(10, 94, 85, 55);
		contentPane.add(btnIadeEt);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(292, 29, 454, 196);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			
			
			

			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				txtid.setText(table.getValueAt(i, 0).toString());
				bookId = Integer.parseInt(txtid.getText());
				
				
				
			}
		});
		table.setModel(new DefaultTableModel(
				new Object[][] 
				{}, 
				new String[] {
						"id", "Book Name", "Author Name", "Author Surname"
				}));
		scrollPane.setViewportView(table);
		
		txtid = new JTextField();
		txtid.setBounds(149, 203, 96, 19);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setBounds(10, 209, 45, 13);
		contentPane.add(lblNewLabel);
		
		txtduration = new JTextField();
		txtduration.setColumns(10);
		txtduration.setBounds(149, 229, 96, 19);
		contentPane.add(txtduration);
		
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setBounds(10, 235, 85, 13);
		contentPane.add(lblDuration);
		
		JButton btnOgrenciKitaplar = new JButton("Ogrenci Kitapları");
		btnOgrenciKitaplar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				Object[] column = new Object[3];
				model.setRowCount(0);
				List<Book> books = student.get().getBooks();
				
				for (int i = 0; i < books.size(); i++) {
					column[0] = books.get(i).getId();
					column[1] = books.get(i).getDetail().getTitle();
					column[2] = books.get(i).getAuthor().getFirstname();
					column[3] = books.get(i).getAuthor().getLastname();
					model.addRow(column);
				}
			}
		});
		btnOgrenciKitaplar.setBounds(99, 94, 124, 55);
		contentPane.add(btnOgrenciKitaplar);
		
		JButton btnTumKitaplar = new JButton("Tum Kitaplar");
		btnTumKitaplar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTable();
			}
		});
		btnTumKitaplar.setBounds(99, 26, 124, 55);
		contentPane.add(btnTumKitaplar);
		getTable();
	}

	public void getTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] column = new Object[4];
		model.setRowCount(0);
		List<Book> books = bookService.listAll()
				.stream()
				.filter(bookDetail -> !bookDetail.getDetail().isBorrowed())
				.collect(Collectors.toList());
		
		books.forEach(book-> System.out.println(book.getId() + " " + book.getDetail().getTitle()));
		for (int i = 0; i < books.size(); i++) {
			column[0] = books.get(i).getId();
			column[1] = books.get(i).getDetail().getTitle();
			column[2] = books.get(i).getAuthor().getFirstname();
			column[3] = books.get(i).getAuthor().getLastname();
			model.addRow(column);
		}
	}
}
