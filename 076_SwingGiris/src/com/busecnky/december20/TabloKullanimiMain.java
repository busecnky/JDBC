package com.busecnky.december20;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TabloKullanimiMain {

	private JFrame frmBenimIlkUygulamam;
	private JTable table;
	private JTextField textField_Ad;
	private JTextField textField_ID;
	private JTextField textField_Soyad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabloKullanimiMain window = new TabloKullanimiMain();
					window.frmBenimIlkUygulamam.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TabloKullanimiMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	private void initialize() {
		frmBenimIlkUygulamam = new JFrame();
		frmBenimIlkUygulamam.setTitle("Benim ilk uygulamam");
		frmBenimIlkUygulamam.setBounds(100, 100, 922, 626);
		frmBenimIlkUygulamam.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBenimIlkUygulamam.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(126, 195, 688, 274);
		frmBenimIlkUygulamam.getContentPane().add(scrollPane);
		
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
		
		JButton btnNewButton = new JButton("ArrayList'den bilgi çek");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Bir array list oluşturup içine user nesneleri ekleyeceğim
				//Daha sonra bu arraylistin içeriğini table'a yerleştireceğim.
				ArrayList<User> list = new ArrayList<>();
				User user1 = new User(1,"John","Nash");
				User user2 = new User(2,"Smith","Watson");
				User user3 = new User(3,"Alfred","Rock");
				list.add(user1);
				list.add(user2);
				list.add(user3);
				
				//Table a bu arraylisti insert edeceğim
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				Object[] columns = new Object[3];
				for (int i = 0; i < list.size(); i++) {
					columns[0]= list.get(i).getId();
					columns[1]= list.get(i).getAd();
					columns[2]= list.get(i).getSoyad();
					model.addRow(columns);
				}
				
			}
		});
		btnNewButton.setBounds(25, 508, 247, 23);
		frmBenimIlkUygulamam.getContentPane().add(btnNewButton);
		
		textField_Ad = new JTextField();
		textField_Ad.setBounds(243, 81, 130, 26);
		frmBenimIlkUygulamam.getContentPane().add(textField_Ad);
		textField_Ad.setColumns(10);
		
		textField_ID = new JTextField();
		textField_ID.setBounds(243, 47, 130, 26);
		frmBenimIlkUygulamam.getContentPane().add(textField_ID);
		textField_ID.setColumns(10);
		
		textField_Soyad = new JTextField();
		textField_Soyad.setBounds(243, 119, 130, 26);
		frmBenimIlkUygulamam.getContentPane().add(textField_Soyad);
		textField_Soyad.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(146, 52, 61, 16);
		frmBenimIlkUygulamam.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ad");
		lblNewLabel_1.setBounds(146, 86, 61, 16);
		frmBenimIlkUygulamam.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Soyad");
		lblNewLabel_2.setBounds(146, 124, 61, 16);
		frmBenimIlkUygulamam.getContentPane().add(lblNewLabel_2);
		
		JButton btnKayit = new JButton("Kayıt Ekle");
		btnKayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) table.getModel(); 
				Object[] columns = new Object[3];
					columns[0]= textField_ID.getText();
					columns[1]= textField_Ad.getText();
					columns[2]= textField_Soyad.getText();
					model.addRow(columns);
			}
		});
		btnKayit.setBounds(445, 47, 117, 29);
		frmBenimIlkUygulamam.getContentPane().add(btnKayit);
		
		JButton btnSil = new JButton("Kayıt Sil");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) table.getModel(); //Silme işlemi için default model kullanmak lazım table model kullarak silemiyoruz!!
				if(table.getSelectedRow() != -1) {
					//remove selected row
					model.removeRow(table.getSelectedRow());
					textField_ID.setText("");
					textField_Ad.setText("");
					textField_Soyad.setText("");
					JOptionPane.showMessageDialog(null, "Selected row deleted successfully"); //Küçük mesaj penceresi
				}
				
				
				
			}
		});
		btnSil.setBounds(445, 81, 117, 29);
		frmBenimIlkUygulamam.getContentPane().add(btnSil);
	}
}











