package com.cognixia.jump.movietracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.Movie;
import com.cognixia.jump.dao.MovieDao;
import com.cognixia.jump.dao.MovieDaoSql;
import com.cognixia.jump.dao.User;
import com.cognixia.jump.dao.UserDao;
import com.cognixia.jump.dao.UserDaoSql;

public class MovieTracker {
	public static void main(String[] args) {
		
		if(login()) { 
			menu();
		}
		else {
			System.out.println("Login Failed, try again.");
		}
	}
	public static boolean login() {
		UserDao userDao = new UserDaoSql();
		
		try {
			userDao.setConnection();
			String username, password;
			
			System.out.println("Login\n");
			Scanner sc = new Scanner(System.in);
			System.out.print("Username: ");
			username = sc.next();
			System.out.println();
			System.out.print("Password: ");
			password = sc.next();
			System.out.println();
			System.out.println("Thanks for entering you username " + username + " we will verify if that account exists.");
			System.out.println("-------------------------------------------------------------------------------------------");
			
			Optional<User> userToFind = userDao.validateUser(username, password);
			
			// check if the optional has something
			if(userToFind.isPresent()) {
				User found = userToFind.get();
				System.out.println("User login Success! Welcome " + found.getFirstName() + "!");
				return true;
			} else {
				System.out.println("Couldnt't find that user. Please try again.");
				return false;
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void menu() {
		MovieDao movieDao = new MovieDaoSql();
		
		try {
			movieDao.setConnection();
			
			List<Movie> moviesList = movieDao.getAllMovies();
			
		String option;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nWelcome to your movies.\n");
		System.out.println("Select a category by entering the number of the category you want to select:");
		System.out.println("1. All Movies");
		System.out.println("2. Movie Genre");
		System.out.println("3. Film Studios");
		System.out.println("4. Add Movie");
		System.out.print("\nUser input: ");
		option = sc.next();
		
		while(!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4")) {
			System.out.println("Invalid input, try again: ");
			System.out.print("\nUser input: ");
			option = sc.next();
		}
		if(option.equals("1")) {
			System.out.println("-------------------------------------------------------------------------------------------");
			System.out.println("\nYou selected: All Movies");
			
			System.out.println("Here is a list of all the movies: ");
			System.out.println("-------------------------------------------------------------------------------------------");
			for(Movie m : moviesList) {
				System.out.println(m.getId() + ". " + m.getTitle());
			}
		}
		else if(option.equals("2")) {
			System.out.println("-------------------------------------------------------------------------------------------");
			System.out.println("\nYou selected: Movie Genre");
			List<String> uniqueGenre = new ArrayList<>();
			
			for(Movie m : moviesList) {
				// This is to find the unique Genre's 
				if(m.getGenre() instanceof String) {
					String genreStr = (String) m.getGenre();
					if(!uniqueGenre.contains(genreStr)) {
						uniqueGenre.add(genreStr);
					}
				}
			}	
			for(String str : uniqueGenre) {
				System.out.println(str); // Printing out unique genres
			}
		}
		else if(option.equals("3")) {
			System.out.println("-------------------------------------------------------------------------------------------");
			System.out.println("\nYou selected: Film Studios");
			List<String> uniqueFilms = new ArrayList<>();
			
			for(Movie m : moviesList) {
				// This is to find the unique Film Studios
				if(m.getFilmStudios() instanceof String) {
					String str = (String) m.getFilmStudios();
					if(!uniqueFilms.contains(str)) {
					uniqueFilms.add(str);
					}
				}
			}
			for(String str : uniqueFilms) {
				System.out.println(str); // Prints out unique film studios
			}
		}
		else if(option.equals("4")) {
			System.out.println("-------------------------------------------------------------------------------------------");
			System.out.println("\nYou selected: Add Movie");
			
			String title, genre, film; int id, length;
			
			Scanner movieScanner = new Scanner(System.in);
			
			System.out.println("Enter movie id: ");
				id = movieScanner.nextInt();
			System.out.println("Enter movie title: ");
				title = movieScanner.next();
			System.out.println("Enter movie genre: ");
				genre = movieScanner.next();
			System.out.println("Enter movie length: ");
				length = movieScanner.nextInt();
			System.out.println("Enter movie film studio: ");
				film = movieScanner.next();
			
			Movie movie = new Movie(id, title, genre, length, film);
			
			System.out.println("Movie Added: " + movie.toString());
		}
		sc.close();
	} catch (ClassNotFoundException | IOException | SQLException e) {
		System.out.println("Could not find any movies.");
		e.printStackTrace();
	}
	} 
}