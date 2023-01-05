package com.december22.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection connect() {

	
        
		Connection connection = null;
    	
		try {
			connection = DriverManager.getConnection(Constant.DbURL, Constant.USERNAME, Constant.PASSWORD);
			System.out.println("Connection established..");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
        }
        System.out.println("Opened database successfully");
		
		
		return connection;
	}
	
	
}
