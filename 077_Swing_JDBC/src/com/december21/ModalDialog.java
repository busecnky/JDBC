package com.december21;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.codec.digest.DigestUtils;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModalDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_kullAdi;
	private JTextField textField_Sifre;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			ModelDialog dialog = new ModelDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

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

	public static boolean verifyUsernamePassword(Connection conn, String username, String password) {

		boolean usernamePasswordCiftiUygunMu = false;

		System.out.println("Trying to connect...");

		String url = "jdbc:postgresql://127.0.0.1:5432/dbtest";

		String kullaniciadi = "postgres";

		String sifre = "4628";

//		//Select and Control
		String sql = "select username, password from users where username = '" + username + "' and password = '"
				+ password + "'";
		PreparedStatement prp;

		try {
			prp = conn.prepareCall(sql);
			ResultSet rs = prp.executeQuery();

			if (rs.next()) {
				usernamePasswordCiftiUygunMu = true;
				JOptionPane.showMessageDialog(null, "Merhaba " + username + "sisteme ho??geldiniz.");
			} else {
				JOptionPane.showMessageDialog(null, "Kullan??c?? bulunamad??, tekrar deneyiniz...");
				usernamePasswordCiftiUygunMu = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usernamePasswordCiftiUygunMu;

	}

	public static void veritabaniBaglantisiniKapat(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println("VT ba??lant??s?? kapat??l??rken hata olu??tu");
			}
		}

		System.out.println("Veri taban?? kapat??lm????t??r");
	}

	/**
	 * Create the dialog.
	 */
	// Normalde bo?? bi constructur getirir.Biz bunu dolduruyoruz!!
	public ModalDialog(JFrame mainFrame, String title, boolean modal) {
		super(mainFrame, title, modal);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnNewButton = new JButton("Login");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Kulla??c?? - ??ifre Kontrol?? yapacaks??n??z
					// ...

					Connection conn = veriTabaninaBaglan();
					boolean sonuc = verifyUsernamePassword(conn, textField_kullAdi.getText(),
							DigestUtils.sha256Hex(textField_Sifre.getText()));

					// E??er kullan??c?? ad?? ve ??ifre kombinasyonu do??ru girildiyse program??n ana
					// ekran?? a????ls??n
					if (sonuc) {
						veriTabaninaBaglan();
						dispose();
					} else {
						textField_kullAdi.setText("");
						textField_Sifre.setText("");

					}

				}
			});
			btnNewButton.setBounds(239, 154, 117, 29);
			contentPanel.add(btnNewButton);

			{
				textField_kullAdi = new JTextField();
				textField_kullAdi.setBounds(226, 65, 130, 26);
				contentPanel.add(textField_kullAdi);
				textField_kullAdi.setColumns(10);
			}

			JLabel lblNewLabel = new JLabel("Kullan??c?? Ad??:");
			lblNewLabel.setBounds(101, 70, 113, 16);
			contentPanel.add(lblNewLabel);

			JLabel lblifre = new JLabel("??ifre:");
			lblifre.setBounds(101, 108, 61, 16);
			contentPanel.add(lblifre);

			textField_Sifre = new JTextField();
			textField_Sifre.setColumns(10);
			textField_Sifre.setBounds(226, 103, 130, 26);
			contentPanel.add(textField_Sifre);
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					JButton cancelButton = new JButton("Cancel");

					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							// mainFrame.dispose(); //E??er DatabaseApplicationda 1. yol gibi yaparsak bunu
							// a????yoruz alttakini kap??yoruz.
							System.exit(ABORT); // E??er DatabaseApplicationda 2. yol gibi yaparsak bunu kap??yoruz
												// ??sttekini a????yoruz.
						}
					});
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				}
			}
		}
	}
}
