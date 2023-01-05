package com.december30.view;

import java.awt.EventQueue;

 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.december30.entity.Student;
import com.december30.service.StudentService;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Optional;
import java.awt.event.ActionEvent;

 

public class StudentLogin extends JFrame {

	private JPanel contentPane;
	private JTextField tf_username;
	private JPasswordField tf_pass;
	private StudentService studentService;
	
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentLogin frame = new StudentLogin();
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
	public StudentLogin() {
		this.studentService = new StudentService();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		tf_username = new JTextField();
		tf_username.setBounds(158, 98, 140, 20);
		contentPane.add(tf_username);
		tf_username.setColumns(10);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(36, 101, 71, 14);
		contentPane.add(lblNewLabel);

		tf_pass = new JPasswordField();
		tf_pass.setBounds(158, 159, 140, 20);
		contentPane.add(tf_pass);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(36, 162, 71, 14);
		contentPane.add(lblNewLabel_1);

		JButton btn_login = new JButton("Login");
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = tf_username.getText();
				String password = tf_pass.getText();
				Optional<Student> student = studentService.findByUserName(username);
				if (student.isPresent()) {
					if (student.get().getPassword().equals(password)) {
						StudentManager studentManager = new StudentManager(student);
						studentManager.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Lütfen şifreyi kontrol edin");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Lütfen usernamei kontrol edin");
				}
			}
		});
		btn_login.setBounds(158, 229, 140, 23);
		contentPane.add(btn_login);
	}
}