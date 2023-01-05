package com.december22.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.december22.entity.Personel;

public class PersonelRepository {

	
	
	public ArrayList<Personel> getAllPersonel() {
		
		ArrayList<Personel> personelList = veritabanindanVeriCek("select * from person");
		
		System.out.println(personelList);
		return personelList;
	}

	public ArrayList<Personel> getPersonelById(String id) {
		ArrayList<Personel> personelList = veritabanindanVeriCek("select * from person where id = " + id);
		
		System.out.println(personelList);
		return personelList;
	}

	
	private ArrayList<Personel> veritabanindanVeriCek(String sqlkomut) {

		
		Connection conn = veriTabaninaBaglan();
		PreparedStatement prp;
		
		ArrayList<Personel> list = new ArrayList<>();
		
		try {
			prp = conn.prepareCall(sqlkomut);
			ResultSet rs = prp.executeQuery();
			
			while(rs.next()) {
				Personel p = new Personel(rs.getInt("id"), rs.getString("ad"), rs.getString("soyad"));
				list.add(p);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			//Veritabanı bağlantısını kapat
			veriTabaniBaglantisiniKapat(conn);
		
		return list;
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

	
	
}
