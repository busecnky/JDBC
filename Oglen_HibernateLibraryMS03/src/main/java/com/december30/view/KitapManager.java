package com.december30.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.december30.entity.Author;
import com.december30.entity.Book;
import com.december30.entity.BookDetail;
import com.december30.service.AuthorService;
import com.december30.service.BookDetailService;
import com.december30.service.BookService;
import com.december30.service.StudentService;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KitapManager extends JFrame {

	private JPanel contentPane;
	private JTextField tf_kitapIsmi;
	private JTextField tf_yazarIsmi;
	private JTextField tf_yazarSoyismi;
	private BookService bookService;
	private AuthorService authorService;
	private BookDetailService bookDetailService;
	private StudentService studentService;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KitapManager frame = new KitapManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	private int id;
	
	public KitapManager() {
		bookService = new BookService();
		authorService = new AuthorService();
		bookDetailService = new BookDetailService();
		studentService = new StudentService();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Kitap İsmi");
		lblNewLabel.setBounds(70, 63, 92, 16);
		contentPane.add(lblNewLabel);

		JLabel lblyazarIsmi = new JLabel("Yazar İsmi");
		lblyazarIsmi.setBounds(70, 100, 92, 16);
		contentPane.add(lblyazarIsmi);

		JLabel lblYazarSoyismi = new JLabel("Yazar Soyismi");
		lblYazarSoyismi.setBounds(70, 137, 92, 16);
		contentPane.add(lblYazarSoyismi);

		tf_kitapIsmi = new JTextField();
		tf_kitapIsmi.setBounds(199, 58, 130, 26);
		contentPane.add(tf_kitapIsmi);
		tf_kitapIsmi.setColumns(10);

		tf_yazarIsmi = new JTextField();
		tf_yazarIsmi.setBounds(199, 95, 130, 26);
		contentPane.add(tf_yazarIsmi);
		tf_yazarIsmi.setColumns(10);

		tf_yazarSoyismi = new JTextField();
		tf_yazarSoyismi.setBounds(199, 132, 130, 26);
		contentPane.add(tf_yazarSoyismi);
		tf_yazarSoyismi.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Author author;
				Book book = new Book();
				BookDetail bookDetail = new BookDetail();

				String title = tf_kitapIsmi.getText();
				String authorName = tf_yazarIsmi.getText();
				String authorSurname = tf_yazarSoyismi.getText();

				bookDetail.setTitle(title);
				bookDetailService.create(bookDetail);

				author = authorService.findByName(authorName, authorSurname);
				if (author == null) {
					Author author2 = new Author(authorName, authorSurname);
					authorService.create(author2);
					book.setAuthor(author2);
				} else {
					book.setAuthor(author);
				}
				book.setDetail(bookDetail);
				bookService.create(book);
				getTable();
			}
		});
		btnSave.setBounds(70, 238, 117, 29);
		contentPane.add(btnSave);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(341, 62, 453, 168);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
	
				id = Integer.parseInt(table.getValueAt(row, 0).toString());
				
			}
		});
		table.setModel(new DefaultTableModel(
				new Object[][] 
				{}, 
				new String[] {
						"Id", "Book Name", "Author Name", "Return Date"
				}));
		scrollPane.setViewportView(table);
		
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		scrollPane.setViewportView(table);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookService.delete(id);
			}
		});
		btnDelete.setBounds(199, 238, 117, 29);
		contentPane.add(btnDelete);
		
		
		

		getTable();
	}

	
	public void getTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] column = new Object[4];
		model.setRowCount(0);
		List<Book> books = bookService.listAll();
		
		books.forEach(book-> System.out.println(book.getId() + " " + book.getDetail().getTitle()));
		for (int i = 0; i < books.size(); i++) {
			column[0] = books.get(i).getId();
			column[1] = books.get(i).getDetail().getTitle();
			column[2] = books.get(i).getAuthor().getFirstname();
			column[3] = books.get(i).getDetail().getBookReturnedDate();
			model.addRow(column);
		}
	}
}
