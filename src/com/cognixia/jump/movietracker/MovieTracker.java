package com.cognixia.jump.movietracker;

import java.util.Scanner;

public class MovieTracker {
	public static void main(String[] args) {
		
		System.out.println("Hello");
		login();
		
		
	}
	public static void login() {
		String username, password;
		
		System.out.println("Login");
		System.out.println();
		Scanner sc = new Scanner(System.in);
		System.out.print("Username: ");
		username = sc.next();
		System.out.println();
		System.out.print("Password: ");
		password = sc.next();
		System.out.println();
		System.out.println("Thanks for entering you username " + username + " we will verify if that account exists.");
		
		sc.close();
	}
}
