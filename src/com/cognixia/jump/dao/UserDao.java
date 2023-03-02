package com.cognixia.jump.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public interface UserDao {
	
	public boolean validateUser(User user);

	void setConnection() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException;
	
}
