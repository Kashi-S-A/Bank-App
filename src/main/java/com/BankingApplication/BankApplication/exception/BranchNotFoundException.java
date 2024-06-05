package com.BankingApplication.BankApplication.exception;

public class BranchNotFoundException extends RuntimeException{

	String message;

	public BranchNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
