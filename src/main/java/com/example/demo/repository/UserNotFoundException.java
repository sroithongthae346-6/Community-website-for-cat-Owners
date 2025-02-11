package com.example.demo.repository;

public class UserNotFoundException extends RuntimeException {
	
	public  UserNotFoundException(Long id) {
		super("Could not found user "+id);
	}

}
