package com.BankingApplication.BankApplication.exception;

public class AccountNotFoundException extends RuntimeException {

	String message;

	public AccountNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
