package com.cognixia.jump.movietracker;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.cognixia.jump.dao.Movie;
import com.cognixia.jump.dao.MovieDao;
import com.cognixia.jump.dao.MovieDaoSql;
import com.cognixia.jump.dao.User;
import com.cognixia.jump.dao.UserDao;
import com.cognixia.jump.dao.UserDaoSql;
import com.cognixia.jump.dao.UserMovieProgression;

public class MovieTracker {
	public static void main(String[] args) throws MovieNotFoundException {
		
		if(login()) { 
			menu();
		}
		else {
			System.out.println("Couldnt't find that user. Please try again.");
		}
	}
	
	public static boolean login() {
		UserDao userDao = new UserDaoSql();
		
		try(Scanner sc = new Scanner(System.in)) {
			userDao.setConnection();
			String username, password;
			
			System.out.println("Login\n");
			System.out.print("Username: ");
			username = sc.next();
			System.out.println();
			System.out.print("Password: ");
			password = sc.next();
			System.out.println();
			System.out.println("-------------------------------------------------------------------------------------------");
			
			Optional<User> userToFind = userDao.validateUser(username, password);
			
			// check if the optional has something
			if(userToFind.isPresent()) {
				User found = userToFind.get();
				System.out.println("User login Success! Welcome " + found.getFirstName() + "!");
				List<UserMovieProgression> trackedList = userDao.getListOfMoviesTracked(found);
				System.out.println("Here are the list of movies that you're tracking: \n" + trackedList);
				return true;
			} else {
				return false;
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void menu() throws MovieNotFoundException {
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

		// System.out.println("4. Add Movie");
		System.out.print("\nUser input: ");
		option = sc.next();
		
		while(!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4")) {
			System.out.println("Invalid input, try again: ");
			System.out.print("\nUser input: ");
			option = sc.next();
		}
		if(option.equals("1")) {
			Scanner movieSelector = new Scanner(System.in);
			int selector, maxId = 0;
			
			System.out.println("-------------------------------------------------------------------------------------------");
			System.out.println("\nYou selected: All Movies");
			
			System.out.println("Here is a list of all the movies: ");
			System.out.println("-------------------------------------------------------------------------------------------");
			for(Movie m : moviesList) {
				System.out.println(m.getId() + ". " + m.getTitle());
				maxId++;
			}
			System.out.println("-------------------------------------------------------------------------------------------");
			System.out.print("\nSelect a movie by id: ");
			try {
				selector = movieSelector.nextInt();
				while(selector < 1 || selector > maxId) {
					
					System.out.print("\nSelect a movie by id: ");
					selector = movieSelector.nextInt();
					
					throw new MovieNotFoundException("Movie not found, try again." );
				}
					System.out.println("You selected: " + moviesList.get(selector-1).getTitle());
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid integer.");
			} catch (NumberFormatException e) {
				System.out.println("Invalid input, please enter a valid integer.");
			} catch(IndexOutOfBoundsException e) {
				System.out.println("Please enter a number between 1 and " + maxId);
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
		/*
		else if(option.equals("4")) {
			System.out.println("-------------------------------------------------------------------------------------------");
			System.out.println("\nYou selected: Add Movie");
			
			String title, genre, film; 
			int id = 0, length = 0;
			
			Scanner movieScanner = new Scanner(System.in);
			try {
				System.out.println("Enter movie id: ");
					id = movieScanner.nextInt();
					movieScanner.nextLine();
				System.out.println("Enter movie title: ");
					title = movieScanner.nextLine();
				System.out.println("Enter movie genre: ");
					genre = movieScanner.nextLine();
				System.out.println("Enter movie length: ");
					length = movieScanner.nextInt();
					movieScanner.nextLine();
				System.out.println("Enter movie film studio: ");
					film = movieScanner.nextLine();
					
			Movie movie = new Movie(id, title, genre, length, film);
			
			System.out.println("Movie Added: " + movie.toString());
			
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid integer.");
			}catch (NumberFormatException e) {
				System.out.println("Invalid input, please enter a valid integer.");
			}
		}
		*/
		sc.close();
	} catch (ClassNotFoundException | IOException | SQLException e) {
		System.out.println("Could not find any movies.");
		e.printStackTrace();
	}
	} 
}