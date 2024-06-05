package com.BankingApplication.BankApplication.exception;

public class EmployeeNotFoundException extends RuntimeException{

	String message;
	@Override
	public String getMessage() {
		return message;
	}
	public EmployeeNotFoundException(String message) {
		super();
		this.message = message;
	}
}
