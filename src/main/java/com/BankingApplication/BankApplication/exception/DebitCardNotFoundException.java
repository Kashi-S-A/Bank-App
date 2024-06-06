package com.BankingApplication.BankApplication.exception;

public class DebitCardNotFoundException extends RuntimeException{

	String message;

	public DebitCardNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
