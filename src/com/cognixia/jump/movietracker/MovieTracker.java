package com.cognixia.jump.movietracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.Movie;
import com.cognixia.jump.dao.MovieDao;
import com.cognixia.jump.dao.MovieDaoSql;

public class MovieTracker {
	public static void main(String[] args) {
		
		if(login()) { 
			System.out.println("Login Successful");
			menu();
		}
		else {
			System.out.println("Login Failed, try again.");
		}
	}
	public static boolean login() {
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

		return true;
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
		System.out.print("\nUser input: ");
		option = sc.next();
		
		while(!option.equals("1") && !option.equals("2") && !option.equals("3")) {
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
		sc.close();
	} catch (ClassNotFoundException | IOException | SQLException e) {
		System.out.println("Could not find any movies.");
		e.printStackTrace();
	}
	} 
}