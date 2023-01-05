package com.december22.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.december22.entity.Ogrenci;
import com.december22.entity.Personel;
import com.december22.service.OgrenciService;
import com.december22.service.PersonelService;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PersonelIslemleri {

	private JFrame frame;
	private JTable table;
	private PersonelService personelService;
	private JTextField textField_ID;
	private JTable table_ogr;
	private OgrenciService ogrenciService;
	private JTextField textField_ogrNo;
	private JTextField textField_ogrAd;
	private JTextField textField_ogrSoyad;
	private JTextField textField_ogrCinsiyet;
	private JTextField textField_ogrSinavNotu1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonelIslemleri window = new PersonelIslemleri();
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
	public PersonelIslemleri() {
		initialize();
		personelService = new PersonelService();
		ogrenciService = new OgrenciService();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1145, 541);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(122, 298, 454, 193);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Ad", "Soyad"
				}
			));
		
		scrollPane.setViewportView(table);
		
		JButton btnTumKayitlar = new JButton("Tüm Kayıtları Getir");
		btnTumKayitlar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Personel> list = personelService.getAllPersonel();
				
				tabloyuDoldur(list);
				
			}
		});
		btnTumKayitlar.setBounds(110, 235, 161, 29);
		frame.getContentPane().add(btnTumKayitlar);
		
		textField_ID = new JTextField();
		textField_ID.setBounds(305, 235, 130, 26);
		frame.getContentPane().add(textField_ID);
		textField_ID.setColumns(10);
		
		JButton btnNewButton = new JButton("ID Sorgula");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Personel> list = personelService.getPersonelById(textField_ID.getText());

				if(list == null) {
					JOptionPane.showMessageDialog(null, "Lütfen id yi sayı olarak giriniz.");
				}
				else {
					tabloyuDoldur(list);

				}
				
			}
		});
		btnNewButton.setBounds(468, 235, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(662, 298, 406, 193);
		frame.getContentPane().add(scrollPane_1);
		
		table_ogr = new JTable();
		table_ogr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Database Application dan kopyalıyoruz
				int row = table_ogr.getSelectedRow();
				
				TableModel model = table_ogr.getModel();				
				textField_ogrNo.setText(model.getValueAt(row, 0).toString());
				textField_ogrAd.setText(model.getValueAt(row, 1).toString());
				textField_ogrSoyad.setText(model.getValueAt(row, 2).toString());
				textField_ogrCinsiyet.setText(model.getValueAt(row, 3).toString());
				textField_ogrSinavNotu1.setText(model.getValueAt(row, 4).toString());
				
			}
		});
		
		table_ogr.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Öğr. No", "Ad", "Soyad", "Cinsiyet", "1.Sınav Notu"
				}
			));
		
		
		
		scrollPane_1.setViewportView(table_ogr);
		
		JButton btntumOgrenciler = new JButton("Tüm Öğrencileri Getir");
		btntumOgrenciler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Ogrenci> ogrlist = ogrenciService.getAllOgrenci();
				
				tabloyuDoldurOgrenci(ogrlist);
				
			}
		});
		btntumOgrenciler.setBounds(670, 257, 161, 29);
		frame.getContentPane().add(btntumOgrenciler);
		
		textField_ogrNo = new JTextField();
		textField_ogrNo.setBounds(938, 64, 130, 26);
		frame.getContentPane().add(textField_ogrNo);
		textField_ogrNo.setColumns(10);
		
		textField_ogrAd = new JTextField();
		textField_ogrAd.setBounds(938, 102, 130, 26);
		frame.getContentPane().add(textField_ogrAd);
		textField_ogrAd.setColumns(10);
		
		textField_ogrSoyad = new JTextField();
		textField_ogrSoyad.setBounds(938, 140, 130, 26);
		frame.getContentPane().add(textField_ogrSoyad);
		textField_ogrSoyad.setColumns(10);
		
		textField_ogrCinsiyet = new JTextField();
		textField_ogrCinsiyet.setBounds(938, 178, 130, 26);
		frame.getContentPane().add(textField_ogrCinsiyet);
		textField_ogrCinsiyet.setColumns(10);
		
		textField_ogrSinavNotu1 = new JTextField();
		textField_ogrSinavNotu1.setBounds(938, 216, 130, 26);
		frame.getContentPane().add(textField_ogrSinavNotu1);
		textField_ogrSinavNotu1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Öğr. No:");
		lblNewLabel.setBounds(840, 69, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ad:");
		lblNewLabel_1.setBounds(840, 107, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Soyad:");
		lblNewLabel_2.setBounds(840, 145, 61, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Cinsiyet");
		lblNewLabel_3.setBounds(840, 183, 61, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Sınav Notu 1:");
		lblNewLabel_4.setBounds(840, 221, 61, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
		JButton btn_Sil = new JButton("Sil");
		btn_Sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Boolean sonuc = ogrenciService.kayitSil(textField_ogrNo.getText());
				
				if(!sonuc) {
					JOptionPane.showMessageDialog(null, "Girilen öğrenci no formatı hatalıdır!");
				}else {
					ArrayList<Ogrenci> ogrlist = ogrenciService.getAllOgrenci();
					tabloyuDoldurOgrenci(ogrlist);
					ogrenciBilgileriAlaniniTemizle();
				}
				
			}
		});
		btn_Sil.setBounds(850, 257, 49, 29);
		frame.getContentPane().add(btn_Sil);
		
		JButton btn_Ekle = new JButton("Ekle");
		btn_Ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Ogrenci ogrenci = new Ogrenci(Integer.parseInt(textField_ogrNo.getText()), textField_ogrAd.getText(), textField_ogrSoyad.getText(),
										textField_ogrCinsiyet.getText(), Integer.parseInt(textField_ogrSinavNotu1.getText()));
				ogrenciService.kayitEkle(ogrenci);
				ArrayList<Ogrenci> ogrlist = ogrenciService.getAllOgrenci();
				tabloyuDoldurOgrenci(ogrlist);
				
				
			}
		});
		btn_Ekle.setBounds(915, 257, 80, 29);
		frame.getContentPane().add(btn_Ekle);
	}
	
	
	protected void ogrenciBilgileriAlaniniTemizle() {
		textField_ogrNo.setText("");
		textField_ogrAd.setText("");
		textField_ogrSoyad.setText("");
		textField_ogrCinsiyet.setText("");
		textField_ogrSinavNotu1.setText("");
		
	}

	public void tabloyuDoldur(ArrayList<Personel> list) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		model. setRowCount(0);    // GUIdeki TABLONUN ICERIGiNI SIFIRLAR

		Object[] columns = new Object[3];
		for (int i = 0; i < list.size(); i++) {
			columns[0]= list.get(i).getId();
			columns[1]= list.get(i).getAd();
			columns[2]= list.get(i).getSoyad();
			model.addRow(columns);
		}
	}
	
	
	public void tabloyuDoldurOgrenci(ArrayList<Ogrenci> list) {
		DefaultTableModel model = (DefaultTableModel) table_ogr.getModel();
		
		model. setRowCount(0);    // GUIdeki TABLONUN ICERIGiNI SIFIRLAR

		Object[] columns = new Object[5];
		for (int i = 0; i < list.size(); i++) {
			columns[0]= list.get(i).getOgrencino();
			columns[1]= list.get(i).getAd();
			columns[2]= list.get(i).getSoyad();
			columns[3]= list.get(i).getCinsiyet();
			columns[4]= list.get(i).getSinavnotu1();
			model.addRow(columns);
		}
	}
}
