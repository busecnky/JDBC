package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection connect() {
	
		
		final String url = "jdbc:postgresql://localhost:5432/productdb";
		final String username = "postgres";
		final String password = "4628";
        
		Connection connection = null;
    	
		try {
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connection established..");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
        }
        System.out.println("Opened database successfully");
		
		
		return connection;
	}
	
	
}
