package com.cognixia.jump.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cognixia.jump.connection.ConnectionManager;

public class UserDaoSql implements UserDao{

	// Connection used for all methods
	private Connection conn;

	@Override
	public void setConnection() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		conn = ConnectionManager.getConnection();
	}
	
	@Override
	public boolean validateUser(User user) {
		boolean status = false;
		try( PreparedStatement pstmt = conn.prepareStatement("select * from user where username = ? and password = ?");){
			  pstmt.setString(1, user.getUsername());
	          pstmt.setString(2, user.getPassword());
	          
	          ResultSet rs = pstmt.executeQuery();
	          status = rs.next();
	          
	          
		} catch (SQLException e) {
			System.out.println("Cannot verify user due to connection issues");
			//e.printStackTrace();
			return false;
		}
		
		return status;
	}
}
