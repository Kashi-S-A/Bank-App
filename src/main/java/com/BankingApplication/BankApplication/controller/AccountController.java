package com.BankingApplication.BankApplication.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BankingApplication.BankApplication.entity.Account;
import com.BankingApplication.BankApplication.entity.Branch;
import com.BankingApplication.BankApplication.entity.Statement;
import com.BankingApplication.BankApplication.response.ResponseStructure;
import com.BankingApplication.BankApplication.service.AccountService;
import com.BankingApplication.BankApplication.service.ExportStatementsExcel;
import com.BankingApplication.BankApplication.service.SaveAccountStatementsToPDF;

import jakarta.servlet.http.HttpServletResponse;

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
	
	//update balance
	@PatchMapping("/updateBalance/accountNumber/{accountNumber}")
	public String updateBalance(@PathVariable int accountNumber,@RequestParam double balance) {
		return accountService.updateBalance(accountNumber, balance);
	}
	
	//transfer amount
	@PutMapping("/transferamount/fromAccountNumber/{fromAccountNumber}/toAccountNumber/{toAccountNumber}")
	public ResponseEntity<ResponseStructure<Statement>> tranferAmount(@PathVariable int fromAccountNumber,@PathVariable int toAccountNumber,@RequestParam double amount) {
		return accountService.transferAmount(fromAccountNumber, toAccountNumber, amount);
	}
	
	//get statements
	@GetMapping("/getstatements")
	public ResponseEntity<ResponseStructure<List<Statement>>> getAllStatements(@RequestParam int accountNumber) {
		return accountService.getAllStatements(accountNumber);
	}
	
	@Autowired
	private ExportStatementsExcel excel;
	
	@GetMapping("/sheet/accountNumber/{accountNumber}")
	public void generateExcelStatementSheet(HttpServletResponse response,@PathVariable int accountNumber) throws IOException {
		
		response.setContentType("application/octet-stream");
		
		String headerKey="Content-Disposition";
		String headerValue="attachment;filename=accountTransaction.xls";
		
		response.setHeader(headerKey, headerValue);
		
		excel.exportDatExcel(accountNumber,response);
	}
	
	@Autowired
	private SaveAccountStatementsToPDF accountStatementsToPDF;
	
	@GetMapping("/statementsPdf")
	public void SaveAccountStatementsToPDF(@RequestParam int accountNumber) throws FileNotFoundException {
		accountStatementsToPDF.saveStatementsToPDF(accountNumber);
	}
}
