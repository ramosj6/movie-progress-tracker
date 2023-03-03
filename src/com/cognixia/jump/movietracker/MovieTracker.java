package com.cognixia.jump.movietracker;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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

	private static List<UserMovieProgression> trackedList;

	private static MovieDao movieDao;
	
	// Keeps track of the user for the application
	private static User userFound;
	private static UserDao userDao;
	public static void main(String[] args) throws MovieNotFoundException, InvalidOptionException {
		
		if(login()) { 
			menu();
		}
		else {
			System.out.println("Couldnt't find that user. You have one more login attempt. Please try again!");
			// only want to do two attempts -- last attempt
			if(login()) {
				menu();
			} else {
				System.out.println("Too many login attempts. You are now locked out. Terminating program");
			}
		}
	}

	public static boolean login() {
		userDao = new UserDaoSql();
		
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
			
			
			Optional<User> userToFind = userDao.validateUser(username, password);
			
			// check if the optional has something
			if(userToFind.isPresent()) {
				userFound = userToFind.get();
				System.out.println("-------------------------------------------------------------------------------------------");
				System.out.println("User login Success! Welcome " + userFound.getFirstName() + "!");
				trackedList = userDao.getListOfMoviesTracked(userFound);
				return true;
			} else {
				return false;
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void menu() throws MovieNotFoundException, InvalidOptionException {
			movieDao = new MovieDaoSql();
		
		try {
			movieDao.setConnection();
			
			List<Movie> moviesList = movieDao.getAllMovies();
			
			String option;
			Scanner sc = new Scanner(System.in);
			Scanner firstSelector = new Scanner(System.in);
			Scanner secondSelector = new Scanner(System.in);
			System.out.println("\nWelcome to your Movie Progress Tracker!\n");
			System.out.println("Here are the list of movies that you're tracking:");
			
			getTrackedList();
		
			System.out.println();
			System.out.println("===========================================================================================");
			System.out.println("Please select an option :");
			System.out.println("1) Add Movie To Track");
			System.out.println("2) Update Movie Progress");
			System.out.println("3) Genre");
			System.out.println("4) Film Studios");
			System.out.println("5) Exit/Log out");
			System.out.println("===========================================================================================");

			System.out.print("\nUser input: ");
			option = sc.next();
	
		
		while(!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4") && !option.equals("5")) {
			System.out.print("\nUser input: ");
			option = sc.next();
			throw new InvalidOptionException ("Invalid input, try again please.");
		}
			if(option.equals("1")) {
				addMovieOption(moviesList, firstSelector);
				getTrackedList();
			}
			else if(option.equals("2")) {
				
				updateMovieProgress(moviesList, secondSelector);
				getTrackedList();
			}
			else if(option.equals("3")) {
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
			else if(option.equals("4")) {
				System.out.println("-------------------------------------------------------------------------------------------");
				System.out.println("\nYou selected: Film Studios");
				List<String> uniqueFilms = new ArrayList<>();
				
			}
			else if(option.equals("5")) {
				System.out.println("-------------------------------------------------------------------------------------------");
				System.out.println("\nYou terminated, good day sir.");
				firstSelector.close();
				secondSelector.close();
			}	
			if(!option.equals("5")) {
				menu();
			}
			//sc.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println("Could not find any movies.");
			e.printStackTrace();
		}
	}
	
	private static String progressionStatus(String status) {
		if(status.equalsIgnoreCase("NC")) {
			return "Not Completed";
		} else if(status.equalsIgnoreCase("IP")) {
			return "In-progress";
		} else if(status.equalsIgnoreCase("C")) {
			return "Completed";
		} else {
			return "Unknown status";
		}
	}
	
	private static void updateMovieProgress(List<Movie> movies, Scanner sc) {
		int selector;
		System.out.println("-------------------------------------------------------------------------------------------");
		System.out.println("Here are the movies that you are tracking: ");
		getTrackedList();
		System.out.println("Which movie would you like to update its progress?(Please choose movie id)");
		try {
			boolean found = false;
			selector = sc.nextInt();
			for(UserMovieProgression u: trackedList) {
				if(selector == u.getMovieId()) {
					found = true;
					break; // breaking out of the loop
				}
			}
			while(!found || selector < 1 || selector > movies.size()) {
				System.out.print("\nPlease select a valid movie from your tracking list.");
				selector = sc.nextInt();
				for(UserMovieProgression u: trackedList) {
					if(selector == u.getMovieId()) {
						found = true;
						break; // breaking out of the loop
					}
				}
			}
			
			Movie movieChosen = movies.get(selector-1);	
			System.out.println("You selected: " + movieChosen.getTitle());
			
			System.out.println("How would you like to change the status?");
			System.out.println("(Note: Enter abbreviations only) NC-Not Complete, IP-In Progress, C-Complete");
			String updatedStatus = sc.next();
			while(!updatedStatus.equalsIgnoreCase("NC") && !updatedStatus.equalsIgnoreCase("IP") && !updatedStatus.equalsIgnoreCase("C")) {
				System.out.print("\nPlease select a valid Abbreviation!");
				updatedStatus = sc.next();
			}
			System.out.println("Updating the status of your movie...\n");

			//Updating the status in the database
			if(userDao.updateMovieProgress(userFound, selector, updatedStatus)) {
				// Updating the tracked list
				trackedList = userDao.getListOfMoviesTracked(userFound);
				
				System.out.println("Updated list: \n");
			} else {
				System.out.println("Could not update the status of your movie");
			}
			

		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a valid type.");
		} catch (NumberFormatException e) {
			System.out.println("Invalid input, please enter a valid integer.");
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Please enter a number between 1 and " + movies.size());
		}

	}
	
	private static void addMovieOption(List<Movie> movies, Scanner sc) throws MovieNotFoundException, SQLIntegrityConstraintViolationException {
		int selector;
		System.out.println("-------------------------------------------------------------------------------------------");
		System.out.println("Here are the list of movies you can choose from: \n");
		for(Movie m : movies) {
			System.out.println(m.getId() + ". " + m.getTitle());
		}
		System.out.println();
		System.out.println("Which movie would you like to keep track of?(Please input movie value/id)");
		
		try {
			selector = sc.nextInt();
			while(selector < 1 || selector > movies.size()) {
				
				System.out.print("\nPlease select a valid movie from the list: ");
				selector = sc.nextInt();
				
				throw new MovieNotFoundException("Movie not found, try again." );
			}
			System.out.println("You selected: " + movies.get(selector-1).getTitle());
			System.out.println("Adding to your tracker...\n");
			
			// If successfully added the movie
			if (userDao.addMovieForProgress(userFound, selector)) {
				// Updates the current list with newly added movie to track
				System.out.println("Here's your updated list now:\n");
				// Updating the tracked list
				trackedList = userDao.getListOfMoviesTracked(userFound);
			} else {
				System.out.println("Did not add the movie.");
				System.out.println("Here's your list\n");
			}

		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a valid integer.");
		} catch (NumberFormatException e) {
			System.out.println("Invalid input, please enter a valid integer.");
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Please enter a number between 1 and " + movies.size());
		}
		
	}
	
	private static void getTrackedList() {
		for(UserMovieProgression u: trackedList) {
			Optional<Movie> movie = movieDao.getMovieById(u.getMovieId());
			//checking if exists
			if(movie.isPresent()) {
				Movie movieFound = movie.get();
				System.out.println(movieFound.getId() + ". " + movieFound.getTitle() + ", Current status: " + progressionStatus(u.getStatus()));
			}
		}
	}
	
}