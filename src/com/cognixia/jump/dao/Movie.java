package com.cognixia.jump.dao;

public class Movie {
	
	// private variables for movie
	private int id;
	
	private String title;
	
	private String genre;
	
	private int lengthMin;
	
	private String filmStudios;

	public Movie(int id, String title, String genre, int lengthMin, String filmStudios) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.lengthMin = lengthMin;
		this.filmStudios = filmStudios;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getLengthMin() {
		return lengthMin;
	}

	public void setLengthMin(int lengthMin) {
		this.lengthMin = lengthMin;
	}

	public String getFilmStudios() {
		return filmStudios;
	}

	public void setFilmStudios(String filmStudios) {
		this.filmStudios = filmStudios;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", genre=" + genre + ", lengthMin=" + lengthMin
				+ ", filmStudios=" + filmStudios + "]";
	}
	
	

}
