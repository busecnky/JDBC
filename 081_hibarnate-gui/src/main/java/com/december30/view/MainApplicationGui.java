package com.december30.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.december30.entity.Student;
import com.december30.service.StudentService;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainApplicationGui {

	private JFrame frame;
	private JTable table;
	private JTextField tf_id;
	private JTextField tf_ad;
	private JTextField tf_soyad;
	private JTextField tf_email;
	private StudentService studentService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApplicationGui window = new MainApplicationGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainApplicationGui() {
		initialize();
		
		studentService = new StudentService();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 735, 597);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(102, 317, 564, 195);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				
				TableModel model = table.getModel();				
				tf_id.setText(model.getValueAt(row, 0).toString());
				tf_ad.setText(model.getValueAt(row, 1).toString());
				tf_soyad.setText(model.getValueAt(row, 2).toString());
				tf_email.setText(model.getValueAt(row, 3).toString());
				
			}
		});
		
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "First Name", "Last Name", "Email"
				}
			));
		scrollPane.setViewportView(table);
		
		JButton btnGetAll = new JButton("Get All");
		btnGetAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<Student> list = studentService.getAll();
				tabloyuDoldur(list);
				
			}
		});
		btnGetAll.setBounds(549, 204, 117, 29);
		frame.getContentPane().add(btnGetAll);
		
		tf_id = new JTextField();
		tf_id.setEditable(false);
		tf_id.setBounds(209, 63, 188, 26);
		frame.getContentPane().add(tf_id);
		tf_id.setColumns(10);
		
		tf_ad = new JTextField();
		tf_ad.setBounds(209, 101, 188, 26);
		frame.getContentPane().add(tf_ad);
		tf_ad.setColumns(10);
		
		tf_soyad = new JTextField();
		tf_soyad.setColumns(10);
		tf_soyad.setBounds(209, 139, 188, 26);
		frame.getContentPane().add(tf_soyad);
		
		tf_email = new JTextField();
		tf_email.setColumns(10);
		tf_email.setBounds(209, 177, 188, 26);
		frame.getContentPane().add(tf_email);
		
		JLabel lblNewLabel = new JLabel("ID: ");
		lblNewLabel.setBounds(102, 73, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblAd = new JLabel("Ad:");
		lblAd.setBounds(102, 111, 61, 16);
		frame.getContentPane().add(lblAd);
		
		JLabel lblSoyad = new JLabel("Soyad:");
		lblSoyad.setBounds(102, 149, 61, 16);
		frame.getContentPane().add(lblSoyad);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(102, 187, 61, 16);
		frame.getContentPane().add(lblEmail);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Student student = new Student(tf_ad.getText(), tf_soyad.getText(), tf_email.getText());
				studentService.save(student);
				List<Student> list = studentService.getAll();	
				tabloyuDoldur(list);
			}
		});
		btnSave.setBounds(420, 204, 117, 29);
		frame.getContentPane().add(btnSave);
		
		JButton btnSorgula = new JButton("Sorgula");
		btnSorgula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Student> list = studentService.getByName(tf_ad.getText());				
				tabloyuDoldur(list);
			}
		});
		btnSorgula.setBounds(420, 101, 117, 29);
		frame.getContentPane().add(btnSorgula);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
					studentService.update(tf_id.getText(), tf_ad.getText(), tf_soyad.getText(), tf_email.getText());
					List<Student> list = studentService.getAll();	
					tabloyuDoldur(list);
			}
		});
		btnUpdate.setBounds(420, 261, 117, 29);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					studentService.delete(tf_id.getText());
					
					List<Student> list = studentService.getAll();	
					tabloyuDoldur(list);
					textFieldlariBosalt();
			}
		});
		btnDelete.setBounds(549, 261, 117, 29);
		frame.getContentPane().add(btnDelete);
		
		JButton btnNewButton = new JButton("Alanları boşalt");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldlariBosalt();
			}
		});
		btnNewButton.setBounds(549, 101, 117, 29);
		frame.getContentPane().add(btnNewButton);
	}
	
	
	public void tabloyuDoldur(List<Student> list) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		model. setRowCount(0);    // GUIdeki TABLONUN ICERIGiNI SIFIRLAR

		Object[] columns = new Object[4];
		for (int i = 0; i < list.size(); i++) {
			columns[0]= list.get(i).getId();
			columns[1]= list.get(i).getFirstName();
			columns[2]= list.get(i).getLastName();
			columns[3]= list.get(i).getEmail();
			
			model.addRow(columns);
		}
	}
	
	private void textFieldlariBosalt() {
		tf_id.setText("");
		tf_ad.setText("");
		tf_soyad.setText("");
		tf_email.setText("");
		
	}
}
