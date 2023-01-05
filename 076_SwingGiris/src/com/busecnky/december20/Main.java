package com.busecnky.december20;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Color;

public class Main {

	private JFrame frame;
	private JTextField textField;
	
	private JCheckBox chckbxSosis;
	private JCheckBox chckbxRusSalatasi;
	private JCheckBox chckbxMisir;
	private JLabel lbl10;
	private JLabel lbl5;
	private JLabel lbl7;
	private JComboBox comboBoxSosis;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_3;
	private JLabel lblToplam;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JComboBox comboBoxRus;
	private JComboBox comboBoxMisir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 922, 608);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		chckbxSosis = new JCheckBox("Sosis");
		chckbxSosis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secimlereGoreTextHazirla();
			}
		});
		chckbxSosis.setBounds(76, 60, 128, 23);
		frame.getContentPane().add(chckbxSosis);
		
		chckbxRusSalatasi = new JCheckBox("Rus Salatası");
		chckbxRusSalatasi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secimlereGoreTextHazirla();
			}
		});
		chckbxRusSalatasi.setBounds(78, 101, 128, 23);
		frame.getContentPane().add(chckbxRusSalatasi);
		
		chckbxMisir = new JCheckBox("Mısır");
		chckbxMisir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secimlereGoreTextHazirla();
			}
		});
		chckbxMisir.setBounds(79, 140, 128, 23);
		frame.getContentPane().add(chckbxMisir);
		
		JButton btnNewButton = new JButton("Seçimleri Yazdır");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secimlereGoreTextHazirla();
			}
		});
		btnNewButton.setBounds(76, 530, 150, 29);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(266, 530, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lbl10 = new JLabel("10");
		lbl10.setBounds(242, 64, 29, 16);
		frame.getContentPane().add(lbl10);
		
		lbl5 = new JLabel("5");
		lbl5.setBounds(242, 105, 61, 16);
		frame.getContentPane().add(lbl5);
		
		lbl7 = new JLabel("7");
		lbl7.setBounds(242, 144, 61, 16);
		frame.getContentPane().add(lbl7);
		
		comboBoxSosis = new JComboBox();
		comboBoxSosis.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		comboBoxSosis.setBounds(358, 60, 82, 27);
		frame.getContentPane().add(comboBoxSosis);
		
		comboBoxRus = new JComboBox();
		comboBoxRus.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		comboBoxRus.setBounds(358, 101, 82, 27);
		frame.getContentPane().add(comboBoxRus);
		
		comboBoxMisir = new JComboBox();
		comboBoxMisir.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		comboBoxMisir.setBounds(358, 140, 82, 27);
		frame.getContentPane().add(comboBoxMisir);
		
		btnNewButton_1 = new JButton("Toplam Ücreti Hesapla");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secimlereGoreFiyat();
				
			}
		});
		btnNewButton_1.setBounds(76, 190, 226, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		lblNewLabel_3 = new JLabel("Toplam Ücret:");
		lblNewLabel_3.setBounds(99, 252, 115, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblToplam = new JLabel("0 TL");
		lblToplam.setBounds(266, 252, 61, 16);
		frame.getContentPane().add(lblToplam);
		
		lblNewLabel_5 = new JLabel("Sade Kumpir (50 TL)");
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_5.setBounds(88, 22, 188, 16);
		frame.getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Adet");
		lblNewLabel_6.setBounds(358, 22, 61, 16);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel = new JLabel("TL");
		lblNewLabel.setBounds(285, 64, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("TL");
		lblNewLabel_1.setBounds(285, 105, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("TL");
		lblNewLabel_2.setBounds(285, 144, 61, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		
	}
	
	
	public void secimlereGoreFiyat() {
		int toplam = 50;
		if (chckbxSosis.isSelected()){
		toplam = toplam + Integer.parseInt(lbl10.getText()) * Integer.parseInt(comboBoxSosis.getSelectedItem().toString());
		}
		if (chckbxRusSalatasi.isSelected()){
			toplam = toplam + Integer.parseInt(lbl5.getText()) * Integer.parseInt(comboBoxRus.getSelectedItem().toString());
		}
		if (chckbxMisir.isSelected()){
			toplam = toplam + Integer.parseInt(lbl7.getText()) * Integer.parseInt(comboBoxMisir.getSelectedItem().toString());
		}
		lblToplam.setText(String.valueOf(toplam));
	}
	
	public void secimlereGoreTextHazirla() {
		String secim = "";
		if (chckbxSosis.isSelected()){
		secim = secim + "Sosis";
		}
		if (chckbxRusSalatasi.isSelected()){
		secim = secim + " Rus salatası";
		}
		if (chckbxMisir.isSelected()){
		secim = secim + " Mısır";
		}
		textField.setText(secim);
	}
}
