package com.cognixia.jump.connection;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
	
	// by default, the connection will be null
	private static Connection connection;
	
	private static void makeConnection() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		// Properties will be used to access our properties file and read its values
		Properties props = new Properties();
		
		// load in the data from the file using a file stream
		props.load(new FileInputStream("resources/config.properties"));
		
		// save the values as variables from the properties file
		String url = props.getProperty("url");
		String username = props.getProperty("username");
		String password = props.getProperty("password");
		
		// establish the connection
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(url, username, password);
	}
	
	//returns the single connection object stored in the class
	public static Connection getConnection() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		//not make the connection again if we are already connected
		if(connection == null) {
			makeConnection();
		}

		return connection;
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to our Program!");
		System.out.println("Establishing db connection...");
		try {
			Connection connection = ConnectionManager.getConnection();
			System.out.println("Connection made to the movie database!");
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't load detail for connection, can't make connection");
		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't load driver, can't make connection");
		} catch (IOException e) {
			System.out.println("Couldn't load connection details, can't make connection");
		} catch (SQLException e) {
			System.out.println("Couldn't connect to the db");
		}
	}

}
