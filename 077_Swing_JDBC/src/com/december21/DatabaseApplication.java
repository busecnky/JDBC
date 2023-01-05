package com.december21;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DatabaseApplication {

	private JFrame frame;
	private JTable table;
	private JTextField textField_ID;
	private JTextField textField_Ad;
	private JTextField textField_Soyad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseApplication window = new DatabaseApplication();
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
	public DatabaseApplication() {
		initialize();
		veriTabanindanVeriCekveGoster();
		
		//Şifre ekranında giremeyip Cancel tuşuna basınca bu frame gelmesin için:
		
		//1.yol:
		//frame.setVisible(true);   //Yukarıda 	window.frame.setVisible(true);    kodunu yoruma alıp buraya bu satırı yazarsak
								//CANCEL tuşuna tıkladığımız zaman gider.
		
		//Ya da modaldialogdan abort dicez

		
		ModalDialog md = new ModalDialog(frame, "Modal Pencere", true);
		md.setVisible(true);
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 854, 708);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(75, 208, 494, 203);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int row = table.getSelectedRow();
				
				TableModel model = table.getModel();				
				textField_ID.setText(model.getValueAt(row, 0).toString());
				textField_Ad.setText(model.getValueAt(row, 1).toString());
				textField_Soyad.setText(model.getValueAt(row, 2).toString());
				
				
			}
		});
		
		
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Ad", "Soyad"
				}
			));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Tüm Kayıtları Getir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				veriTabanindanVeriCekveGoster();
			}
		});
		btnNewButton.setBounds(75, 131, 161, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(239, 25, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ad");
		lblNewLabel_1.setBounds(239, 53, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Soyad");
		lblNewLabel_2.setBounds(239, 81, 61, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_ID = new JTextField();
		textField_ID.setBounds(312, 20, 130, 26);
		frame.getContentPane().add(textField_ID);
		textField_ID.setColumns(10);
		
		textField_Ad = new JTextField();
		textField_Ad.setBounds(312, 48, 130, 26);
		frame.getContentPane().add(textField_Ad);
		textField_Ad.setColumns(10);
		
		textField_Soyad = new JTextField();
		textField_Soyad.setBounds(312, 79, 130, 26);
		frame.getContentPane().add(textField_Soyad);
		textField_Soyad.setColumns(10);
		
		JButton btnSil = new JButton("Kaydı Sil");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = veriTabaninaBaglan();
				
				delete(conn);
				veriTabanindanVeriCekveGoster();
			}
		});
		btnSil.setBounds(391, 131, 117, 29);
		frame.getContentPane().add(btnSil);
		
		JButton btnKayit_Ekle = new JButton("Kayıt Ekle");
		btnKayit_Ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection conn = veriTabaninaBaglan();
				insert(conn);
				veriTabanindanVeriCekveGoster();
			}
		});
		btnKayit_Ekle.setBounds(263, 131, 117, 29);
		frame.getContentPane().add(btnKayit_Ekle);
		
		JButton btnGuncelle = new JButton("Kayıt Güncelle");
		btnGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection conn = veriTabaninaBaglan();
				update(conn);
				veriTabanindanVeriCekveGoster();
				
			}
		});
		btnGuncelle.setBounds(520, 131, 117, 29);
		frame.getContentPane().add(btnGuncelle);
		
		JButton btn_idSorgula = new JButton("ID Sorgula");
		btn_idSorgula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idSorgula();
			}
		});
		btn_idSorgula.setBounds(497, 20, 117, 29);
		frame.getContentPane().add(btn_idSorgula);
		
		JButton btn_adSorgula = new JButton("Ad Sorgula");
		btn_adSorgula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adSorgula();
			}
			
		});
		btn_adSorgula.setBounds(497, 48, 117, 29);
		frame.getContentPane().add(btn_adSorgula);
		
		JButton btn_soyadSorgula = new JButton("Soyad Sorgula");
		btn_soyadSorgula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soyadSorgula();
			}
		});
		btn_soyadSorgula.setBounds(497, 76, 117, 29);
		frame.getContentPane().add(btn_soyadSorgula);
		
		JButton btnYeniPencere = new JButton("Yeni Pencere Aç");
		btnYeniPencere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EkPencere frame = new EkPencere();
				frame.setVisible(true);
			}
		});
		btnYeniPencere.setBounds(637, 371, 161, 29);
		frame.getContentPane().add(btnYeniPencere);
		
		JButton btnNewButton_1 = new JButton("Modal Pencere Aç");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ModalDialog md = new ModalDialog(frame, "Modal Pencere", true);
				md.setVisible(true);
		
			}
		});
		btnNewButton_1.setBounds(637, 330, 161, 29);
		frame.getContentPane().add(btnNewButton_1);
	}

	
	
	public void soyadSorgula() {
		String sqlKomut = "select*from person where ad ilike '" + textField_Soyad.getText() + "%'";	

		sorgulamaYapveTabloyuGuncelle(sqlKomut);
		
	}
	
	
	public void adSorgula() {
		String sqlKomut = "select*from person where ad ilike '" + textField_Ad.getText() + "%'";	

		sorgulamaYapveTabloyuGuncelle(sqlKomut);
		
	}
	
	public void idSorgula() {
		String sqlKomut = "select*from person where id =" + textField_ID.getText();
		
		 	sorgulamaYapveTabloyuGuncelle(sqlKomut);
	
	}
	
	public void sorgulamaYapveTabloyuGuncelle(String sqlKomut) {
		ArrayList<User> list = veriTabanindanVeriCek(sqlKomut);
	

		
		// Table'a bu arrayllisti insert edecegim.
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		// Her bilgileri getir butonuna tiklandaganda Tablonun icerigi temizlenir
		model. setRowCount(0);    // TABLO ICERIGiNI SIFIRLAR
		
		
	
	
		Object[] columns = new Object[3];
		for (int i = 0; i < list.size(); i++) {
			columns[0]= list.get(i).getId();
			columns[1]= list.get(i).getAd();
			columns[2]= list.get(i).getSoyad();
			model.addRow(columns);
		}
	}
	

	

	private ArrayList<User> veriTabanindanVeriCek(String sqlKomut) {
	ArrayList<User> list = new ArrayList<>();
		
		Connection conn = veriTabaninaBaglan();
		
		//Verileri VT'den çek ve Arrayliste doldur
		
		//String sqlKomut = "select*from person where id =" + id;
		PreparedStatement prp;
		
		try {
			prp = conn.prepareCall(sqlKomut);
			ResultSet rs = prp.executeQuery();
			
			
			while(rs.next()) {
				User u = new User(rs.getInt("id"), rs.getString("ad"), rs.getString("soyad"));
				list.add(u);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(conn != null) 
				try {
				conn.close();
			}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
				}
			}
		
		return list;
	}

	public void update(Connection conn) {
		// VeriTabanından veri güncelleme
		String sqlKomut = "update person set ad = '" + textField_Ad.getText() + "' , soyad = '" + 
						textField_Soyad.getText()+ "' where id= "  + textField_ID.getText();
		
		int sonuc = 0;
		
		try {
			PreparedStatement prp = conn.prepareCall(sqlKomut);
			sonuc = prp.executeUpdate();
			
			if(sonuc == 0) {
				JOptionPane.showMessageDialog(null, "ID = " + textField_ID.getText() + " numaralı personel mevcut değil!");
				textField_ID.setText("");
				textField_Ad.setText("");
				textField_Soyad.setText("");
			}else {
				JOptionPane.showMessageDialog(null, "Güncelleme başarılıdır");

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(conn != null) 
				try {
				conn.close();
			}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
				}
			}
		
	}

	public void insert(Connection conn) {
		// VeriTabanından veri silme
				String sqlKomut = "insert into person values('" + textField_ID.getText() +  "' , '" + textField_Ad.getText() + "' , '" +
							textField_Soyad.getText() + "')";
				
				try {
					PreparedStatement prp = conn.prepareCall(sqlKomut);
					prp.executeUpdate();
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					if(conn != null) 
						try {
						conn.close();
					}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
						}
					}
		
	}

	private void delete(Connection conn) {
		// VeriTabanından veri silme
		String sql = "delete from person where id = " + textField_ID.getText();
		
		try {
			PreparedStatement prp = conn.prepareCall(sql);
			prp.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(conn != null) 
				try {
				conn.close();
			}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
				}
			}
		
		
	}
	
	protected void veriTabanindanVeriCekveGoster() {
		
		// Bir ArrayList olusturup icine User nesneleri ekleyecegim.
		// Daha sonra bu arraylistin iserigini table'a yerlestirecegim.
		ArrayList<User> list = veriTabanindanVeriCek();
		
		// Table'a bu arrayllisti insert edecegim.
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		// Her bilgileri getir butonuna tiklandaganda Tablonun icerigi temizlenir
		model. setRowCount(0);    // GUIdeki TABLONUN ICERIGiNI SIFIRLAR
		
		
	
	
		Object[] columns = new Object[3];
		for (int i = 0; i < list.size(); i++) {
			columns[0]= list.get(i).getId();
			columns[1]= list.get(i).getAd();
			columns[2]= list.get(i).getSoyad();
			model.addRow(columns);
		}
		
	}

	

	public static ArrayList<User> veriTabanindanVeriCek() {
		ArrayList<User> list = new ArrayList<>();
		
		Connection conn = veriTabaninaBaglan();
		
		//Verileri VT'den çek ve Arrayliste doldur
		
		String sqlKomut = "select*from person";
		PreparedStatement prp;
		
		try {
			prp = conn.prepareCall(sqlKomut);
			ResultSet rs = prp.executeQuery();
			
			
			while(rs.next()) {
				User u = new User(rs.getInt("id"), rs.getString("ad"), rs.getString("soyad"));
				list.add(u);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(conn != null) 
				try {
				conn.close();
			}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
				}
			}
		
		return list;
	}
	
	
	
	
	public static Connection veriTabaninaBaglan() {
		System.out.println("Trying to connect...");

		String adres = "jdbc:postgresql://127.0.0.1:5432/dbtest";	

		String kullaniciadi = "postgres";

		String sifre = "4628";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(adres, kullaniciadi, sifre);
			System.out.println("Connection established..");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
}
