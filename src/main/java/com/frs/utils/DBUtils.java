package com.frs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static Connection connection;
	
	public static void openConnection() throws SQLException {
		String dbURL = "jdbc:mysql://localhost:3306/advjava";
		
		connection = DriverManager.getConnection(dbURL, "root", "mysql");
		System.out.println("Database Connected!!");
	}
	
	public static void closeConnection() throws SQLException{
		if(connection != null) {
			connection.close();
			System.out.println("Database Closed!!");
		}
	}

	public static Connection getConnection() {
		return connection;
	}
	
	
}
