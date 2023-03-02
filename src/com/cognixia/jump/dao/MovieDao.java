package com.cognixia.jump.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MovieDao {
	// DAO Interface --> list out and outline the functionality that we want with our data
		
	public void setConnection() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException;

	public List<Movie> getAllMovies();
	
	public Optional<Movie> getMovieById(int id);
		
	public boolean createMovie(Movie dept);
		
	public boolean deleteMovie(int id);

	public boolean updateMovie(Movie dept);
}
