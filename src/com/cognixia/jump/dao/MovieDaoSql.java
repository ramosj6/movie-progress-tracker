package com.cognixia.jump.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cognixia.jump.connection.ConnectionManager;

public class MovieDaoSql implements MovieDao{
	
	// Connection used for all methods
	private Connection conn;

	@Override
	public void setConnection() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		conn = ConnectionManager.getConnection();

	}

	@Override
	public List<Movie> getAllMovies() {
		List<Movie> movies = new ArrayList<>();
		try(Statement stmt = conn.createStatement(); 
			ResultSet rs = stmt.executeQuery("Select * from department");){
			while(rs.next()) {
				int id = rs.getInt("movie_id");
				String title = rs.getString("title");
				String genre = rs.getString("genre");
				int length = rs.getInt("length");
				String filmStudios = rs.getString("film_studios");

				
				Movie movie = new Movie(id, title, genre, length, filmStudios);
				
				// adding the movie into the movie list
				movies.add(movie);
			}
			
			
		} catch(SQLException e) {
			// uncomment of you're running into issues and want to know what's
			// going on
//			e.printStackTrace();
		}
		return movies;
	}

	@Override
	public Optional<Movie> getMovieById(int id) {
		
		return Optional.empty();
	}

	@Override
	public boolean createMovie(Movie dept) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteMovie(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateMovie(Movie dept) {
		// TODO Auto-generated method stub
		return false;
	}

}
