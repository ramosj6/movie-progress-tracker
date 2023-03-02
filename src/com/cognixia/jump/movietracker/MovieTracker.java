package com.cognixia.jump.movietracker;

import java.util.Scanner;

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
		String option;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nWelcome to your movies.\n");
		System.out.println("Select a category by entering the number of the category you want to select:");
		System.out.println("1. All Movies");
		System.out.println("2. Film Studios");
		System.out.println("3. Movie Information");
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
		}
		else if(option.equals("2")) {
			System.out.println("-------------------------------------------------------------------------------------------");
			System.out.println("\nYou selected: Film Studios");
		}
		else if(option.equals("3")) {
			System.out.println("-------------------------------------------------------------------------------------------");
			System.out.println("\nYou selected: Movie Information");
		}
		sc.close();
	}
}