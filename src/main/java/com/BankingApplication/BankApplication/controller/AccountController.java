package com.BankingApplication.BankApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BankingApplication.BankApplication.entity.Account;
import com.BankingApplication.BankApplication.entity.Branch;
import com.BankingApplication.BankApplication.response.ResponseStructure;
import com.BankingApplication.BankApplication.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;

	// save
	@PostMapping("/saveaccount")
	public ResponseEntity<ResponseStructure<Account>> saveAccount(@RequestParam int branchId,@RequestBody Account account) {
		return accountService.saveAccount(branchId,account);
	}

	// update Name
	@PatchMapping("/updateName/accountNumber/{accountNumber}")
	public ResponseEntity<ResponseStructure<String>> updateName(@PathVariable int accountNumber, @RequestParam String name) {
		return accountService.updateName(accountNumber, name);
	}

	// update Phone
	@PatchMapping("/updatePhone/accountNumber/{accountNumber}")
	public ResponseEntity<ResponseStructure<String>> updatePhone(@PathVariable int accountNumber, @RequestParam long phone) {
		return accountService.updatePhone(accountNumber, phone);
	}
	
	//get account by id
	@GetMapping("/getaccount")
	public ResponseEntity<ResponseStructure<Account>> getAccount(@RequestParam int accountNumber) {
		return accountService.getAccountByAccountNumber(accountNumber);
	}
	
	//get all the accounts
	@GetMapping("/getallaccountsbybranch")
	public ResponseEntity<ResponseStructure<List<Account>>> getAllAccount(@RequestParam int branchId){
		return accountService.getAllAccountsByBranch(branchId);
	}
	
	//get branch by account number
	@GetMapping("/getbranchbyaccount")
	public ResponseEntity<ResponseStructure<Branch>> getBranchByAccount(@RequestParam long accountNumber) {
		return accountService.getBranchByAccount(accountNumber);
	}
	
	//get number of accounts in a branch
	@GetMapping("/getcountbranchaccounts")
	public ResponseEntity<ResponseStructure<Integer>> getCountBranchAccounts(@RequestParam int branchId) {
		return accountService.getCountBranchAccounts(branchId);
	}
	
	//delete account
	@DeleteMapping("/deleteaccount")
	public ResponseEntity<ResponseStructure<String>> deleteAccount(@RequestParam int accountNumber) {
		return accountService.deleteAccount(accountNumber);
	}
	
}
