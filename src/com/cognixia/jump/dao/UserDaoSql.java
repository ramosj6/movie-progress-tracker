package com.cognixia.jump.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cognixia.jump.connection.ConnectionManager;

public class UserDaoSql implements UserDao{

	// Connection used for all methods
	private Connection conn;

	@Override
	public void setConnection() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		conn = ConnectionManager.getConnection();
	}

	@Override
	public Optional<User> validateUser(String username, String password) {
		try( PreparedStatement pstmt = conn.prepareStatement("select * from user where username = ? and password = ?");){
			  pstmt.setString(1, username);
	          pstmt.setString(2, password);
	          
	          ResultSet rs = pstmt.executeQuery();
	          
	          if( rs.next() ) {
	        	  int userId = rs.getInt("user_id");
	        	  String firstName = rs.getString("first_name");
	        	  String lastName = rs.getString("last_name");
	        	  String email = rs.getString("email");
	        	  String usrname = rs.getString("username");
	        	  String pass = rs.getString("password");
	        	  
	        	  rs.close();
	        	  
	        	  // Creating the User object
	        	  User user = new User(userId, firstName, lastName, email, usrname, pass);
	        	  
	        	  // placing it in the Optional
	        	  Optional<User> userFound = Optional.of(user);
	        	  
	        	  return userFound;
	          } else {
	        	  rs.close();
	        	  return Optional.empty();
	          }
	          
	          
		} catch (SQLException e) {
			System.out.println("Cannot verify user due to connection issues");
			//e.printStackTrace();
			return Optional.empty();
		}
	}
	
	// This method will add a movie into the movie_user table where the progression is tracked
	@Override
	public boolean addMovieForProgress(User user, int movie_id) {
		try( PreparedStatement pstmt = conn.prepareStatement("insert into user_movie(user_id, movie_id, status)"
				+ " values(?, ?, ?)")){
			pstmt.setInt(1, user.getUserId());
			pstmt.setInt(2, movie_id);
			pstmt.setString(3, "NC"); // Set to be "not complete"
			
			int count = pstmt.executeUpdate();
			if(count > 0) { // update happened
				return true;
			}

		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	@Override
	public boolean updateMovieProgress(User user, int movie_id, String newStatus) {
		try(PreparedStatement pstmt = conn.prepareStatement("update user_movie set status = ? where user_id = ? and movie_id = ?");){
			pstmt.setString(1, newStatus);
			pstmt.setInt(2, user.getUserId());
			pstmt.setInt(3, movie_id);

			int count = pstmt.executeUpdate();
			if(count > 0) {
				return true;
			}

		} catch(SQLException e) {
			return false;
		}
		return false;
	}	
	
	public List<UserMovieProgression> getListOfMoviesTracked(User user) {
		List<UserMovieProgression> moviesTracked = new ArrayList<>();
		try(PreparedStatement pstmt = conn.prepareStatement("select * from user_movie where user_id = ?");){
			pstmt.setInt(1, user.getUserId());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int userId = rs.getInt("user_id");
				int movieId = rs.getInt("movie_id");
				String status = rs.getString("status");

				UserMovieProgression tracked = new UserMovieProgression(userId, movieId, status);
				
				// adding the movie into the movie list
				moviesTracked.add(tracked);
			}
			
			
		} catch(SQLException e) {
			// uncomment of you're running into issues and want to know what's
			// going on
//			e.printStackTrace();
		}
		return moviesTracked;
	}
	
	
}
