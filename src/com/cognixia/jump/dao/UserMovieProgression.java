package com.cognixia.jump.dao;

public class UserMovieProgression {
	
	private int userId;
	private int movieId;
	private String status;
	
	public UserMovieProgression(int userId, int movieId, String status) {
		super();
		this.userId = userId;
		this.movieId = movieId;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "UserMovieProgression [userId=" + userId + ", movieId=" + movieId + ", status=" + status + "]";
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
