package com.cognixia.jump.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao {
	
	public Optional<User> validateUser(String username, String password);
	
	public boolean addMovieForProgress(User user, int movie_id);

	public boolean updateMovieProgress(User user, int movie_id, String newStatus);
	
	public List<UserMovieProgression> getListOfMoviesTracked(User user);

	void setConnection() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException;
	
}
