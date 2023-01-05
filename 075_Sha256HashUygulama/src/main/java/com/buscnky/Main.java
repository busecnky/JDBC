package com.buscnky;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.codec.digest.DigestUtils;

public class Main {

	public static void main(String[] args) {
		

		
		Scanner sc = new Scanner(System.in);
		
		String username = "";
		String password = "";
		
		Connection conn = veriTabaninaBaglan(); //Veritabanına bağlanma işlemi

		
		do {
			System.out.println("Enter username: ");
			username = sc.nextLine();
			System.out.println("Enter password: ");
			password = sc.nextLine();
			
		} while (!verifyUsernamePassword(conn, username, DigestUtils.sha256Hex(password)));
		
		System.out.println("Sisteme hoşgeldiniz...");
		
		
		int secim = 9;
		
		do {
			System.out.println("Öğrenci bilgi sistemine hoşgeldiniz...");
			System.out.println("**************************************");
			System.out.println("1. Yeni Kayıt");
			System.out.println("2. Silme");
			System.out.println("3. Güncelleme");
			System.out.println("4. Tüm öğrencileri listele");
			System.out.println("5. Tüm kız öğrencileri listele");
			System.out.println("6. Tüm erkek öğrencileri listele");
			System.out.println("7. Tüm sınav ortalamaları 50'nin üzerinde olan öğrencileri listele");
			System.out.println("8. Belirli bir ilde yaşayan öğrencileri listele");
			System.out.println("9. Çıkış");
			
			Scanner scanner = new Scanner(System.in);
			secim = scanner.nextInt();
			
			switch (secim) {
			case 1:
				yeniKayit(conn, scanner);
				break;
			case 2:
				silme(conn, scanner);
				break;
			case 3:
				guncelleme(conn, scanner);
				break;
			case 4:
				tumOgrencileriListele(conn);
				break;
			case 5:
				tumOgrencileriListele(conn, "K");
				break;
			case 6:
				tumOgrencileriListele(conn, "E");
				break;
			case 7:
				ogrencileriNotaGoreListele(conn, scanner);
				break;
			case 8:
				
				break;

			default:
				break;
			}
			
			
		} while (secim != 9);
		
		
		//VERİ TABANI BAĞLANTISI KAPATMA
	
		veritabaniBaglantisiniKapat(conn);

		
		
		//hash a String with Sha-256 bit algorithm
		
/*		String s = DigestUtils.sha256Hex("123");
		System.out.println("Ali'nin şifresi 123'ün SHA256 ile hashlenmiş hali: " + s);
		

		String s2 = DigestUtils.sha256Hex("321");
		System.out.println("Veli'nin şifresi 321'in SHA256 ile hashlenmiş hali: " + s2);
*/
	}

	
	public static void veritabaniBaglantisiniKapat(Connection conn) {
		if(conn != null) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("VT bağlantısı kapatılırken hata oluştu");
			}
		}
		
		System.out.println("Veri tabanı kapatılmıştır");
	}
	

	public static boolean verifyUsernamePassword(Connection conn, String username, String password) {
		
		boolean usernamePasswordCiftiUygunMu = false;
		
		System.out.println("Trying to connect...");

		String url = "jdbc:postgresql://127.0.0.1:5432/dbtest";	

		String kullaniciadi = "postgres";

		String sifre = "4628";

//		Connection conn = null;
//
//		try {
//			conn = DriverManager.getConnection(url, kullaniciadi, sifre);
//			System.out.println("Connection established..");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		//Select and Control
		String sql = "select username, password from users where username = '" + username  + "' and password = '" + password + "'";
		PreparedStatement prp;
		
		try {
			prp = conn.prepareCall(sql);
			ResultSet rs = prp.executeQuery();
			
			if(rs.next()) {
				usernamePasswordCiftiUygunMu = true;
				System.out.println("Merhaba " + username + "sisteme hoşgeldiniz.");
			}else {
				System.out.println("Kullanıcu bulunamadı, tekrar deneyiniz...");
				usernamePasswordCiftiUygunMu = false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return usernamePasswordCiftiUygunMu;
		
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

	public static void yeniKayit(Connection conn, Scanner sc) {
		sc.nextLine();
		
		System.out.println("Lütfen öğrenci no giriniz: ");
		int ogrNo = sc.nextInt();
		sc.nextLine();

		System.out.println("Lütfen öğrenci adını giriniz: ");
		String ad = sc.nextLine();
		System.out.println("Lütfen öğrenci soyadını giriniz: ");
		String soyad = sc.nextLine();
		System.out.println("Lütfen öğrenci doğum tarihini giriniz: ");
		String dogumtarihi = sc.nextLine();
		Date dt = Date.valueOf(dogumtarihi);
		System.out.println("Lütfen öğrenci cinsiyetini giriniz: ");
		String cinsiyet = sc.nextLine();
		
		String sqlkomut = "insert into ogrenci (ogrencino, ad, soyad, dogumtarihi, cinsiyet) values(?, ?, ?, ?, ?)";
		 
		PreparedStatement prp;
		try {
			prp = conn.prepareCall(sqlkomut);
			prp.setInt(1, ogrNo);
			prp.setString(2, ad);
			prp.setString(3, soyad);
			prp.setDate(4, dt);
			prp.setString(5, cinsiyet);
			
			prp.executeUpdate();   		//---> Query'i veritabanına gönder

			System.out.println("Kayıt ekleme işlemi başarılı");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Kayıt işlemi başarısızdır.");

		}
	}
	
	public static void silme(Connection conn, Scanner sc) {

		System.out.println("Lütfen silinecek öğrenci no giriniz: ");
		int ogrNo = sc.nextInt();
		
		
		String sqlkomut = "delete from ogrenci where ogrencino = " + ogrNo;
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

	public static void guncelleme(Connection conn, Scanner sc) {
		
		System.out.println("Lütfen bilgileri güncellenecek öğrenci no giriniz: ");
		int ogrNo = sc.nextInt();
		sc.nextLine();

		
		// Öncelikle güncelleme yapilacak ögrenci ile ilgili halihazarda mevcut bin güncelleme islemi var mi kontrol et
		// eger varsa (yani baska bir kullanic1 bu ögrencinin bilgilerini güncelliyorsa) güncelleme yapilmasina izin verme (daha sonra güncelleyin msj ver)
		String sonuc = ogrencileriListele(conn, ogrNo);
		if (! sonuc.equalsIgnoreCase("olumlu")) {
		return;									//---->Burdaki return ile methoddan çıkar ve üstteki kısma geri döner.
		}
		
		
		//opsiyonel ödev: Bilgileri güncellenecek öğrencinin verilerini print edin
		
		
		//Güncelleme
		System.out.println("Lütfen öğrenci adını giriniz: ");
		String ad = sc.nextLine();
		System.out.println("Lütfen öğrenci soyadını giriniz: ");
		String soyad = sc.nextLine();
		System.out.println("Lütfen öğrenci doğum tarihini giriniz: ");
		String dogumtarihi = sc.nextLine();
		Date dt = Date.valueOf(dogumtarihi);
		System.out.println("Lütfen öğrenci cinsiyetini giriniz: ");
		String cinsiyet = sc.nextLine();
		
		String sqlkomut = "update ogrenci set ad = ?, soyad = ?, dogumtarihi = ?, cinsiyet = ?  where ogrencino = ?";
		PreparedStatement prp;
		
		try {
			prp = conn.prepareCall(sqlkomut);
			prp.setString(1, ad);
			prp.setString(2, soyad);
			prp.setDate(3, dt);
			prp.setString(4, cinsiyet);
			prp.setInt(5, ogrNo);
			
			prp.executeUpdate();
			System.out.println("Güncelleme işlemi başarılı");

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Güncelleme işlemi başarısız");
		}
	
	
	//isleniyormu sütununu tekrar H ile değiştirelim
		
	
	}
	
	public static void isleniyormuSatiriniGuncelle() {
		
	}

	
	public static String ogrencileriListele(Connection conn, int ogrNo) {
		String sqlkomut = "select*from ogrenci where ogrencino = " + ogrNo;

		PreparedStatement prp;
		String isleniyormu = "ÖğrenciYok";
		String sonuc = "";
		
	
		try {
			prp = conn.prepareCall(sqlkomut);
			ResultSet rs = prp.executeQuery();

			if (rs.next()) {
				isleniyormu = rs.getString("isleniyormu");
				if(isleniyormu.equalsIgnoreCase("E")) {
					sonuc = "Başka bir işlem yapılmaktadır, daha sonra deneyiniz.";
				}else if (isleniyormu.equalsIgnoreCase("H")) {
					String sqlkomutUpdate = "update ogrenci set isleniyormu = 'E' where ogrencino =" + ogrNo;
					PreparedStatement prpUpdate;
					prpUpdate = conn.prepareCall(sqlkomutUpdate) ;
					prpUpdate.executeUpdate();
					sonuc = "olumlu";
				}
				

			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return sonuc;

	}
	
	
	public static void tumOgrencileriListele(Connection conn) {
		String sqlkomut = "select*from ogrenci";

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

	public static void tumOgrencileriListele(Connection conn, String cinsiyet) {
		String sqlkomut = "select*from ogrenci where cinsiyet = '" + cinsiyet + "'";

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
	

	public static void ogrencileriNotaGoreListele(Connection conn, Scanner sc) {
		System.out.println("Hangi not ortalamasının üzerindeki öğrencileri listelemek istiyorsunuz giriniz: ");
		int not = sc.nextInt();
		
		String sqlkomut = "select*from ogrenci where (sinavnotu1 + sinavnotu2 + finalnotu)/3 > " + not ;

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
	
	public static void ogrencileriSehireGoreListele(Connection conn, Scanner sc) {
		System.out.println("Hangi şehirdeki öğrencileri listelemek istiyorsunuz giriniz: ");
		int sehir = sc.nextInt();
		
		String sqlkomut = "select*from ogrenci where ogrencino in (select ogrencino from adres where sehir = " + sehir + ")";

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
}
