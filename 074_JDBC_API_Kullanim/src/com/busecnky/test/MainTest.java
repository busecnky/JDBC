package com.busecnky.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainTest {
	
	//Şifrelerde maven kullanılmalıdır. Hoca daha sonra anlatacak

	public static void select(Connection conn) {
		// JDBC API Tutoriallarında var bunlar ordan bakarak yazılabilir, ezberlermeye
		// gerek yok

		// SELECT
		String sqlkomut = "select*from ogrenci";
		PreparedStatement prp;

		// Ben gerekli hazırlıkları yapıyorum sen çağır diyoruz bura
		try {
			prp = conn.prepareCall(sqlkomut);
			ResultSet rs = prp.executeQuery(); // ----> Queryi çalıştır. prp.executeQuery(); demek ama
												// resultset döndürmezsek eğer sonuçları görüemeyiz

			while (rs.next()) {
				// 1.yol
				System.out.print("Öğrenci no: " + rs.getInt("ogrencino"));
				System.out.print(" Ad: " + rs.getString("ad"));
				System.out.print(" Soyad: " + rs.getString("soyad"));
				System.out.print(" Doğum tarihi: " + rs.getString("dogumtarihi"));
				System.out.print(" Cinsiyet: " + rs.getString("cinsiyet"));
				System.out.print(" Sınav Notu1: " + rs.getInt("sinavnotu1"));
				System.out.print(" Sınav Notu2: " + rs.getInt("sinavnotu2"));
				System.out.println(" Final Notu: " + rs.getInt("finalnotu"));

				// 2.yol
//						System.out.print("Öğrenci no: " + rs.getInt(1));
//						System.out.print(" Ad: " + rs.getString(2));
//						System.out.print(" Soyad: " + rs.getString(3));
//						System.out.print(" Doğum tarihi: " + rs.getString(4));
//						System.out.print(" Cinsiyet: " + rs.getString(5));
//						System.out.print(" Sınav Notu1: " + rs.getInt(6));
//						System.out.print(" Sınav Notu2: " + rs.getInt(7));
//						System.out.println(" Final Notu: " + rs.getInt(8));
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static void selectOgrenciNo(Connection conn, int ogrno) {
	
		String sqlkomut = "select*from ogrenci where ogrencino = " + ogrno;
		PreparedStatement prp;

	
		try {
			prp = conn.prepareCall(sqlkomut);
			ResultSet rs = prp.executeQuery(); 

			while (rs.next()) {
				// 1.yol
				System.out.print("Öğrenci no: " + rs.getInt("ogrencino"));
				System.out.print(" Ad: " + rs.getString("ad"));
				System.out.print(" Soyad: " + rs.getString("soyad"));
				System.out.print(" Doğum tarihi: " + rs.getString("dogumtarihi"));
				System.out.print(" Cinsiyet: " + rs.getString("cinsiyet"));
				System.out.print(" Sınav Notu1: " + rs.getInt("sinavnotu1"));
				System.out.print(" Sınav Notu2: " + rs.getInt("sinavnotu2"));
				System.out.println(" Final Notu: " + rs.getInt("finalnotu"));

			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static void select(Connection conn, String ad) {
		
		String sqlkomut = "select*from ogrenci where ad = '"+ ad+ "'";

		PreparedStatement prp;

	
		try {
			prp = conn.prepareCall(sqlkomut);
			ResultSet rs = prp.executeQuery(); 

			while (rs.next()) {
				// 1.yol
				System.out.print("Öğrenci no: " + rs.getInt("ogrencino"));
				System.out.print(" Ad: " + rs.getString("ad"));
				System.out.print(" Soyad: " + rs.getString("soyad"));
				System.out.print(" Doğum tarihi: " + rs.getString("dogumtarihi"));
				System.out.print(" Cinsiyet: " + rs.getString("cinsiyet"));
				System.out.print(" Sınav Notu1: " + rs.getInt("sinavnotu1"));
				System.out.print(" Sınav Notu2: " + rs.getInt("sinavnotu2"));
				System.out.println(" Final Notu: " + rs.getInt("finalnotu"));

			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	
	public static void select(Connection conn, String ad, String soyad) {
		
		String sqlkomut = "select*from ogrenci where ad = '" + ad  + "' and soyad = '" + soyad + "'";

		PreparedStatement prp;

	
		try {
			prp = conn.prepareCall(sqlkomut);
			ResultSet rs = prp.executeQuery(); 

			while (rs.next()) {
				// 1.yol
				System.out.print("Öğrenci no: " + rs.getInt("ogrencino"));
				System.out.print(" Ad: " + rs.getString("ad"));
				System.out.print(" Soyad: " + rs.getString("soyad"));
				System.out.print(" Doğum tarihi: " + rs.getString("dogumtarihi"));
				System.out.print(" Cinsiyet: " + rs.getString("cinsiyet"));
				System.out.print(" Sınav Notu1: " + rs.getInt("sinavnotu1"));
				System.out.print(" Sınav Notu2: " + rs.getInt("sinavnotu2"));
				System.out.println(" Final Notu: " + rs.getInt("finalnotu"));

			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	
	public static void selectwithPrepArgs(Connection conn, String ad, String soyad) {
		
		String sqlkomut = "select*from ogrenci where ad = ? and soyad =  ?";

		PreparedStatement prp;

	
		try {
			prp = conn.prepareCall(sqlkomut);
			prp.setString(1, ad);
			prp.setString(2, soyad);
			ResultSet rs = prp.executeQuery();


			while (rs.next()) {
				// 1.yol
				System.out.print("Öğrenci no: " + rs.getInt("ogrencino"));
				System.out.print(" Ad: " + rs.getString("ad"));
				System.out.print(" Soyad: " + rs.getString("soyad"));
				System.out.print(" Doğum tarihi: " + rs.getString("dogumtarihi"));
				System.out.print(" Cinsiyet: " + rs.getString("cinsiyet"));
				System.out.print(" Sınav Notu1: " + rs.getInt("sinavnotu1"));
				System.out.print(" Sınav Notu2: " + rs.getInt("sinavnotu2"));
				System.out.println(" Final Notu: " + rs.getInt("finalnotu"));

			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	
	private static void insert(Connection conn) {

		String sqlkomut = "insert into ogrenci (ogrencino, ad, soyad, dogumtarihi, cinsiyet) values(403, 'Özge', 'Sarım', '2000-08-15', 'K')";
		
		PreparedStatement prp;
		try {
			prp = conn.prepareCall(sqlkomut);
			prp.executeUpdate(); 				//SELECT sorgularında executeQuery diğerlerinde executeUpdate çağırabilirsiniz
			
			System.out.println("Ekleme işlemi başarılı");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Kayıt işlemi başarısızdır.");

		}
	
		
		
	}

	
	private static void okulozetbilgi(Connection conn) {
	
		String sqlkomut = "select*from okulozetbilgi";
		PreparedStatement prp;

	
			try {
				prp = conn.prepareCall(sqlkomut);
				ResultSet rs = prp.executeQuery();
				
				while (rs.next()) {
					int kizogrencisayisi = rs.getInt("kizogrencisayisi");
					int erkekogrencisayisi = rs.getInt("erkekogrencisayisi");
					
					System.out.print("Kız öğrenci sayısı: " + kizogrencisayisi);
					System.out.print(" Erkek öğrenci sayısı: " + erkekogrencisayisi);
					System.out.println();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
	
	public static void update(Connection conn) {
		
		String sqlkomut = "update ogrenci set ad = 'Özgü' where ogrencino = 403";
		PreparedStatement prp;
		
		try {
			prp = conn.prepareCall(sqlkomut);
			prp.executeUpdate(); 	
			System.out.println("Güncelleme işlemi başarılı");

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Güncelleme işlemi başarısız");
		}
	}
	
	public static void delete(Connection conn) {
		
		String sqlkomut = "delete from ogrenci where ogrencino = 403";
		PreparedStatement prp;
		
		try {
			prp = conn.prepareCall(sqlkomut);
			prp.executeUpdate(); 	
			System.out.println("Silme işlemi başarılı");

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Silme işlemi başarısız");
		}
	}
	
	public static void delete(Connection conn, int ogrno) {
		
		String sqlkomut = "delete from ogrenci where ogrencino = " + ogrno;
		PreparedStatement prp;
		
		try {
			prp = conn.prepareCall(sqlkomut);
			prp.executeUpdate(); 	
			System.out.println("Silme işlemi başarılı");

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Silme işlemi başarısız");
		}
	}
	
	
	
	
	public static void main(String[] args) {

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

		// ---------------İŞLEMLERİMİZİ SORGULARIMIZI BURDA
		// YAPIYORUZ------------------------

		
		
//		MainTest.select(conn);
//		MainTest.insert(conn);
//		MainTest.okulozetbilgi(conn);
//		MainTest.update(conn);
//		MainTest.delete(conn);
//		MainTest.delete(conn, 402);
//		MainTest.selectOgrenciNo(conn,114);
//		MainTest.select(conn,"Osman", "Alp");
		MainTest.selectwithPrepArgs(conn, "Osman", "Alp");
		

		// ------------------------------------------------------------------------
		try {
			conn.close();
			System.out.println("Connection closed..");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}