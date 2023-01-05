package busecnky.aralik13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Trying to connect...");
		
		String adres = "jdbc:postgresql://localhost:5432/dbtest";   //localhost= 127.0.0.1 olarak her bilgisayarda telefonda local IP olarak tanımlanır.
																	//WAN  -> IP servis sağlayıcılarla belirlenir.
				
		
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
		
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		
		
		try {
			conn.close();
			System.out.println("Connection closed..");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
