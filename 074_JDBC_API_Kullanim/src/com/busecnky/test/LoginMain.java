package com.busecnky.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginMain {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		String username = "";
		String password = "";
		
		do {
			System.out.println("Enter username: ");
			username = sc.nextLine();
			System.out.println("Enter password: ");
			password = sc.nextLine();
			
		} while (!verifyUsernamePassword(username, password));
		
		System.out.println("Sisteme hoşgeldiniz...");
	}
	
	
	public static boolean verifyUsernamePassword(String username, String password) {
		
		boolean usernamePasswordCiftiUygunMu = false;
		
		System.out.println("Trying to connect...");

		String url = "jdbc:postgresql://127.0.0.1:5432/dbtest";	

		String kullaniciadi = "postgres";

		String sifre = "4628";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, kullaniciadi, sifre);
			System.out.println("Connection established..");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Select and Control
		String sql = "select username, password from users where username = '" + username  + "' and password = '" + password + "')";
		PreparedStatement prp;
		
		try {
			prp = conn.prepareCall(sql);
			ResultSet rs = prp.executeQuery();
			
			if(rs.next()) {
				usernamePasswordCiftiUygunMu = true;
			}else {
				System.out.println("Kullanıcu bulunamadı, tekrar deneyiniz...");
				usernamePasswordCiftiUygunMu = false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return usernamePasswordCiftiUygunMu;
		
	}

	
	
	
	
}
