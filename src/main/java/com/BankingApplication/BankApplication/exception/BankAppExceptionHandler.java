package com.BankingApplication.BankApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.BankingApplication.BankApplication.entity.Account;
import com.BankingApplication.BankApplication.entity.Bank;
import com.BankingApplication.BankApplication.entity.Branch;
import com.BankingApplication.BankApplication.entity.Employee;
import com.BankingApplication.BankApplication.response.ResponseStructure;

@ControllerAdvice
public class BankAppExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ResponseStructure<Employee>> catchEmployeeNotFoundException(EmployeeNotFoundException exception){
		ResponseStructure<Employee> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Not found");
		responseStructure.setMessage(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<Employee>>(responseStructure,HttpStatus.NOT_FOUND);
	
	}
	
	@ExceptionHandler(BankNotFound.class)
	public ResponseEntity<ResponseStructure<Bank>> catchBankNotFoundException(BankNotFound exception) {
		ResponseStructure<Bank> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Not found");
		responseStructure.setMessage(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<Bank>>(responseStructure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BranchNotFoundException.class)
	public ResponseEntity<ResponseStructure<Branch>> catchBranchNotFoundException(BranchNotFoundException exception) {
		ResponseStructure<Branch> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Not found");
		responseStructure.setMessage(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<Branch>>(responseStructure,HttpStatus.NOT_FOUND);
	
	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ResponseStructure<Account>> catchAccountNotFoundException(AccountNotFoundException exception) {
		ResponseStructure<Account> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Not found");
		responseStructure.setMessage(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<Account>>(responseStructure,HttpStatus.NOT_FOUND);
	
	}
	
}
