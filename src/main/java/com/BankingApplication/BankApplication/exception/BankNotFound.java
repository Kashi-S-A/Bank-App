package com.BankingApplication.BankApplication.exception;

public class BankNotFound extends RuntimeException{

	String message;
	
	public BankNotFound(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return "Bank with a given id is not present";
	}
}
