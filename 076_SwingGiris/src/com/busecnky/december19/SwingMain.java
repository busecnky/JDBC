package com.busecnky.december19;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Component;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

public class SwingMain {

	private JFrame frame;
	private JTextField textFieldAd;
	private JTextField textFieldSoyad;
	private JTextField textFieldAdSoyadNo;
	private JTextField textFieldogrNo;
	private JTextField textFieldsayi1;
	private JTextField textFieldsayi2;
	private JTextField Carpim;
	private JTextField textFieldMevsim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingMain window = new SwingMain();
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
	public SwingMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 892, 615);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Hello");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hello Swing!");
				//textFieldAd.setText("Hello Swing!");
			}
		});
		btnNewButton.setBounds(34, 21, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		textFieldAd = new JTextField();
		textFieldAd.setText("-----");
		textFieldAd.setBounds(195, 113, 157, 26);
		frame.getContentPane().add(textFieldAd);
		textFieldAd.setColumns(10);
		
		textFieldSoyad = new JTextField();
		textFieldSoyad.setBounds(195, 151, 157, 26);
		frame.getContentPane().add(textFieldSoyad);
		textFieldSoyad.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Metni Birleştir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String adSoyad = textFieldogrNo.getText() + " " +textFieldAd.getText() + " " + textFieldSoyad.getText();
				textFieldAdSoyadNo.setText(adSoyad);
			}
		});
		btnNewButton_1.setBounds(72, 224, 176, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		textFieldAdSoyadNo = new JTextField();
		textFieldAdSoyadNo.setBounds(273, 224, 231, 26);
		frame.getContentPane().add(textFieldAdSoyadNo);
		textFieldAdSoyadNo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Öğrenci No:");
		lblNewLabel.setBounds(84, 88, 83, 16);
		frame.getContentPane().add(lblNewLabel);
		
		textFieldogrNo = new JTextField();
		textFieldogrNo.setBounds(195, 83, 130, 26);
		frame.getContentPane().add(textFieldogrNo);
		textFieldogrNo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Öğrenci İsim:");
		lblNewLabel_1.setBounds(84, 118, 99, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Öğrenci Soyisim:");
		lblNewLabel_2.setBounds(84, 156, 136, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Sayı 1:");
		lblNewLabel_3.setBounds(597, 161, 61, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Sayı 2:");
		lblNewLabel_4.setBounds(597, 191, 61, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
		JButton carpmaButonu = new JButton("Çarp:");
		carpmaButonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int carpim = Integer.parseInt(textFieldsayi1.getText()) * Integer.parseInt(textFieldsayi2.getText());
				Carpim.setText(String.valueOf(carpim));
				
			}
		});
		carpmaButonu.setBounds(568, 224, 117, 29);
		frame.getContentPane().add(carpmaButonu);
		
		textFieldsayi1 = new JTextField();
		textFieldsayi1.setBounds(680, 156, 130, 26);
		frame.getContentPane().add(textFieldsayi1);
		textFieldsayi1.setColumns(10);
		
		textFieldsayi2 = new JTextField();
		textFieldsayi2.setBounds(680, 186, 130, 26);
		frame.getContentPane().add(textFieldsayi2);
		textFieldsayi2.setColumns(10);
		
		Carpim = new JTextField();
		Carpim.setBounds(697, 224, 130, 26);
		frame.getContentPane().add(Carpim);
		Carpim.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Mevsimler");
		lblNewLabel_5.setBounds(34, 314, 99, 16);
		frame.getContentPane().add(lblNewLabel_5);
		
		JComboBox comboBoxMevsim = new JComboBox();
		comboBoxMevsim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldMevsim.setText(comboBoxMevsim.getSelectedItem().toString());
			}
		});
		comboBoxMevsim.setModel(new DefaultComboBoxModel(new String[] {"Yaz", "Sonbahar", "Kış", "İlkbahar"}));
		comboBoxMevsim.setBounds(196, 310, 168, 27);
		frame.getContentPane().add(comboBoxMevsim);
		
		
		
		JLabel lblNewLabel_6 = new JLabel("Seçiminiz:");
		lblNewLabel_6.setBounds(34, 371, 88, 16);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblMevsim = new JLabel("Yaz");
		lblMevsim.setBounds(147, 371, 110, 16);
		frame.getContentPane().add(lblMevsim);
		
		JButton btnSec = new JButton("Seç");
		btnSec.addActionListener(new ActionListener() {
			int x;
			public void actionPerformed(ActionEvent e) {
				lblMevsim.setText(comboBoxMevsim.getSelectedItem().toString());
			}
		});
		

		btnSec.setBounds(391, 309, 117, 29);
		frame.getContentPane().add(btnSec);
		
		textFieldMevsim = new JTextField();
		textFieldMevsim.setBounds(276, 366, 130, 26);
		frame.getContentPane().add(textFieldMevsim);
		textFieldMevsim.setColumns(10);
		
		//Initialize Ekran elemanları
		textFieldMevsim.setText(comboBoxMevsim.getSelectedItem().toString());
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(595, 333, 168, 83);
		frame.getContentPane().add(textArea);
		
		JButton btnforTextArea = new JButton("New button");
		btnforTextArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textArea.getText() + "Hello!\n");
			}
		});
		btnforTextArea.setBounds(595, 301, 117, 29);
		frame.getContentPane().add(btnforTextArea);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(605, 428, 249, 147);
		frame.getContentPane().add(scrollPane);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(605, 428, 245, 143);
		frame.getContentPane().add(editorPane);
		
		JSlider slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textArea.setText(String.valueOf(slider.getValue()));
			}
		});
		slider.setBounds(34, 416, 190, 29);
		frame.getContentPane().add(slider);
		
		JButton btnSlider = new JButton("Slider");
		btnSlider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(String.valueOf(slider.getValue()));
			}
		});
		btnSlider.setBounds(34, 469, 117, 29);
		frame.getContentPane().add(btnSlider);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setIcon(new ImageIcon(SwingMain.class.getResource("/com/busecnky/javaicon.png")));
		lblNewLabel_7.setBounds(605, 21, 127, 118);
		frame.getContentPane().add(lblNewLabel_7);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setAlignmentY(0.0f);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(SwingMain.class.getResource("/com/busecnky/icon.png")));
		btnNewButton_2.setBounds(374, 21, 212, 191);
		frame.getContentPane().add(btnNewButton_2);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(163, 446, 430, 129);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel_button = new JPanel();
		panel_button.setName("");
		tabbedPane.addTab("Müşteri Kayıt", null, panel_button, null);
		panel_button.setLayout(null);
		
		JButton btnNewButton_3 = new JButton("Müşteri Ekle");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_3.setBounds(19, 19, 168, 29);
		panel_button.add(btnNewButton_3);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(199, 24, 117, 24);
		panel_button.add(textArea_1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Sipariş Kayıt", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_adres = new JPanel();
		tabbedPane.addTab("Adres", null, panel_adres, null);
		panel_adres.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Kampanya", null, panel_3, null);
		panel_3.setLayout(null);
		ImageIcon imageIcon = new ImageIcon();
//		File imageFile = new File("/Users/busecankaya/Desktop/buse/icon.png");
//		Image image =  ImageIO.read(imageFile);
//		Application.getApplication().setDockIconImage(image);
		
	}
}
