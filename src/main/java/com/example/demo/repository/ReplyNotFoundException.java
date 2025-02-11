package com.example.demo.repository;

public class ReplyNotFoundException extends RuntimeException{

	public  ReplyNotFoundException(Long id) {
		super("Could not found Reply "+id);
	}
}
