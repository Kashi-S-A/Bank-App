package com.BankingApplication.BankApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.BankingApplication.BankApplication.response.ResponseStructure;

@ControllerAdvice
public class BankAppExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> catchEmployeeNotFoundException(EmployeeNotFoundException exception){
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Not found");
		responseStructure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK);
	
	}
	
	@ExceptionHandler(BankNotFound.class)
	public ResponseEntity<ResponseStructure<String>> catchBankNotFoundException(BankNotFound exception) {
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Not found");
		responseStructure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK);
	}
	
	@ExceptionHandler(BranchNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> catchBranchNotFoundException(BranchNotFoundException exception) {
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Not found");
		responseStructure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK);
	
	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> catchAccountNotFoundException(AccountNotFoundException exception) {
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Not found");
		responseStructure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK);
	
	}
	
	@ExceptionHandler(DebitCardNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> catchDebitCardNotFoundException(DebitCardNotFoundException exception){
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setMessage("Not found");
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK);
	}
}
