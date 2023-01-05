package com.january2.view;

import java.awt.EventQueue;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Gui_i18n_Ornek {

	private JFrame frame;
	private JButton btnHello;
	private JLabel lblBye;
	private JLabel lblDateTime;
	private JComboBox comboBox;
	private JButton button;
	private JComboBox comboBox_lang;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui_i18n_Ornek window = new Gui_i18n_Ornek();
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
	public Gui_i18n_Ornek() {
		initialize();
		
		Locale.setDefault(new Locale("en","EN"));
		i18n();
	}

	private void i18n() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("com/january2/config/resource_bundle");
		btnHello.setText(resourceBundle.getString("word1"));
		lblBye.setText(resourceBundle.getString("word2"));
		
		//Tarih-saat bilgisini label'a ekle
		Date now = new Date();
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.getDefault());
		System.out.println(df.format(now));
		lblDateTime.setText(df.format(now));
		comboBox.removeAllItems();
		comboBox.addItem(resourceBundle.getString("wordilk"));
		comboBox.addItem(resourceBundle.getString("wordyaz"));
		comboBox.addItem(resourceBundle.getString("wordson"));
		comboBox.addItem(resourceBundle.getString("wordkis"));
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 919, 613);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblDateTime = new JLabel("New label");
		lblDateTime.setBounds(619, 29, 233, 16);
		frame.getContentPane().add(lblDateTime);
		
		btnHello = new JButton("New button");
		btnHello.setBounds(133, 106, 157, 29);
		frame.getContentPane().add(btnHello);
		
		lblBye = new JLabel("New label");
		lblBye.setBounds(338, 111, 121, 16);
		frame.getContentPane().add(lblBye);
		
		comboBox = new JComboBox();
		comboBox.setBounds(238, 184, 194, 27);
		frame.getContentPane().add(comboBox);
		
		JButton buttonLang = new JButton("New button");
		buttonLang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Locale.setDefault(new Locale("en","EN"));
				i18n();
				
			}
		});
		buttonLang.setBounds(569, 417, 117, 29);
		frame.getContentPane().add(buttonLang);
		
		comboBox_lang = new JComboBox();
		comboBox_lang.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboBox_lang.getSelectedItem().toString().equalsIgnoreCase("English")) {
					Locale.setDefault(new Locale("en","EN"));
					i18n();
				}else if(comboBox_lang.getSelectedItem().toString().equalsIgnoreCase("French")) {
					Locale.setDefault(new Locale("fr","FR"));
					i18n();
				}else if(comboBox_lang.getSelectedItem().toString().equalsIgnoreCase("Turkish")) {
					Locale.setDefault(new Locale("tr","TR"));
					i18n();
				}
				
			}
		});
		comboBox_lang.setModel(new DefaultComboBoxModel(new String[] {"English", "French", "Turkish"}));
		comboBox_lang.setBounds(577, 378, 157, 27);
		frame.getContentPane().add(comboBox_lang);
	}
}
