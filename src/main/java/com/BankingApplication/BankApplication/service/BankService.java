package com.BankingApplication.BankApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.BankingApplication.BankApplication.dao.BankDao;
import com.BankingApplication.BankApplication.entity.Bank;
import com.BankingApplication.BankApplication.exception.BankNotFound;
import com.BankingApplication.BankApplication.response.ResponseStructure;

@Service
public class BankService {

	@Autowired
	private BankDao bankDao;

	// save
	public ResponseEntity<ResponseStructure<Bank>> saveBank(Bank bank) {
		Bank bank1 = bankDao.saveBank(bank);
		List<Bank> banks=bankDao.getAllBanks();
		if (bank1 != null) {
			ResponseStructure<Bank> responseStructure = new ResponseStructure<>();
			responseStructure.setData(bank1);
			responseStructure.setMessage("Success");
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Bank>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new BankNotFound("bank does not exist");
		}
	}

	// update
	public ResponseEntity<ResponseStructure<String>> updateBank(int bankId,String bankName) {
		String recieved = bankDao.updateBank(bankId,bankName);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setData(recieved);
		responseStructure.setMessage("Success");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

	// get by id
	public ResponseEntity<ResponseStructure<Bank>> getBankById(int bankId) {
		Bank bank = bankDao.getBankById(bankId).orElseThrow(() -> new BankNotFound("bank does not exist"));
		ResponseStructure<Bank> responseStructure = new ResponseStructure<>();
		responseStructure.setData(bank);
		responseStructure.setMessage("Success");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Bank>>(responseStructure, HttpStatus.OK);
	}

	// get all
	public ResponseEntity<ResponseStructure<List<Bank>>> getAllBanks() {
		List<Bank> banks = bankDao.getAllBanks();
		if (!banks.isEmpty()) {
			ResponseStructure<List<Bank>> responseStructure = new ResponseStructure<>();
			responseStructure.setData(banks);
			responseStructure.setMessage("Success");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Bank>>>(responseStructure, HttpStatus.OK);
		} else {
			throw new BankNotFound("No banks exists");
		}
	}

	//get bank by employee id
		public ResponseEntity<ResponseStructure<Bank>> getBankByEmployeeId(int employeeId) {
			Bank bank= bankDao.getBankByEmployeeId(employeeId);
			if (bank!=null) {
				ResponseStructure<Bank> responseStructure = new ResponseStructure<>();
				responseStructure.setData(bank);
				responseStructure.setMessage("Success");
				responseStructure.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Bank>>(responseStructure, HttpStatus.OK);
			} else {
                throw new BankNotFound("Bank does not exist");
			}
		}
	
	// delete bank
	public ResponseEntity<ResponseStructure<String>> deleteBankById(int bankId) {
		String recieved = bankDao.deleteBankById(bankId);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setData(recieved);
		responseStructure.setMessage("Success");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}
}
