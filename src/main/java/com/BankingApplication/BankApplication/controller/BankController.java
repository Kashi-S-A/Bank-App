package com.BankingApplication.BankApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BankingApplication.BankApplication.entity.Bank;
import com.BankingApplication.BankApplication.response.ResponseStructure;
import com.BankingApplication.BankApplication.service.BankService;

@RestController
public class BankController {

	@Autowired
	private BankService bankService;
	
	//save
	@PostMapping("/savebank")
	public ResponseEntity<ResponseStructure<Bank>> saveBank(@RequestBody Bank bank) {
		return bankService.saveBank(bank);
	}
	
	//update
	@PutMapping("/updatebank")
	public ResponseEntity<ResponseStructure<String>> updateBank(@RequestBody Bank bank) {
		return bankService.updateBank(bank);
	}
	
	//get by id
	@GetMapping("/getbank/{bankId}")
	public ResponseEntity<ResponseStructure<Bank>> getBankById(@PathVariable int bankId) {
		return bankService.getBankById(bankId);
	}
	
	//get all
	@GetMapping("/getallbank")
	public ResponseEntity<ResponseStructure<List<Bank>>> getAllBanks(){
		return bankService.getAllBanks();
	}
	
	//get bank by employee id
	@GetMapping("/getbankbyemployee")
	public ResponseEntity<ResponseStructure<Bank>> getBankByEmployeeId(@RequestParam int employeeId) {
		return bankService.getBankByEmployeeId(employeeId);
	}
	
	//delete bank by id
	@DeleteMapping("/deletebank/{bankId}")
	public ResponseEntity<ResponseStructure<String>> deleteBank(@PathVariable int bankId) {
		return bankService.deleteBankById(bankId);
	}
}
