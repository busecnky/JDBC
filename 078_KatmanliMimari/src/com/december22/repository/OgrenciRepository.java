package com.december22.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.december22.entity.Ogrenci;

public class OgrenciRepository {

	public ArrayList<Ogrenci> getAllOgrenci() {

		ArrayList<Ogrenci> ogrenciList = veritabanindanVeriCek("select ogrencino, ad, soyad, cinsiyet, sinavnotu1 from ogrenci");
		
		System.out.println(ogrenciList);
		return ogrenciList;
	}

	
	
	
	private ArrayList<Ogrenci> veritabanindanVeriCek(String sqlkomut) {

		
		Connection conn = veriTabaninaBaglan();
		PreparedStatement prp;
		
		ArrayList<Ogrenci> ogrlist = new ArrayList<>();
		
		try {
			prp = conn.prepareCall(sqlkomut);
			ResultSet rs = prp.executeQuery();
			
			while(rs.next()) {
				Ogrenci o = new Ogrenci(rs.getInt("ogrencino"), rs.getString("ad"), rs.getString("soyad"),rs.getString("cinsiyet"), rs.getInt("sinavnotu1"));
				ogrlist.add(o);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			//Veritabanı bağlantısını kapat
			veriTabaniBaglantisiniKapat(conn);
		
		return ogrlist;
	}
	
	
	
	
	public void veriTabaniBaglantisiniKapat(Connection conn) {
	
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	
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




	public void delete(String ogrNo) {
	
		veritabaniniGuncelle("delete from ogrenci where ogrencino = " + ogrNo);
		
		
	}

	private void veritabaniniGuncelle(String sqlkomut) {

		
		Connection conn = veriTabaninaBaglan();
		PreparedStatement prp;
		
		
		try {
			prp = conn.prepareCall(sqlkomut);
			prp.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			//Veritabanı bağlantısını kapat
			veriTabaniBaglantisiniKapat(conn);
		

	}




	public void kayitEkle(Ogrenci ogrenci) {
		veritabaniniGuncelle("insert into ogrenci (ogrencino ,ad, soyad, dogumtarihi, cinsiyet, sinavnotu1) values (" +
								ogrenci.getOgrencino() + ", '" + ogrenci.getAd() + "' , '" + ogrenci.getSoyad() + "' , '2000-01-01' , '" + ogrenci.getCinsiyet() 
								+ " ', "  + ogrenci.getSinavnotu1() + ")");
		
	}
	
	
}
