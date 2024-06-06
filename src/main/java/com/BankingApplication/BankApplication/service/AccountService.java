package com.BankingApplication.BankApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.BankingApplication.BankApplication.dao.AccountDao;
import com.BankingApplication.BankApplication.dao.BranchDao;
import com.BankingApplication.BankApplication.dao.StatementDao;
import com.BankingApplication.BankApplication.entity.Account;
import com.BankingApplication.BankApplication.entity.Branch;
import com.BankingApplication.BankApplication.entity.Statement;
import com.BankingApplication.BankApplication.exception.BranchNotFoundException;
import com.BankingApplication.BankApplication.response.ResponseStructure;

@Service
public class AccountService {

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private BranchDao branchDao;

	// save
	public ResponseEntity<ResponseStructure<Account>> saveAccount(int branchId, Account account) {
		Branch branch = branchDao.getBranchById(branchId)
				.orElseThrow(() -> new BranchNotFoundException("Branch does not found"));
		ResponseStructure<Account> responseStructure = new ResponseStructure<>();
		account.setBranch(branch);
		branch.getAccounts().add(account);
		responseStructure.setData(account);
		responseStructure.setMessage("Success");
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		accountDao.saveAccount(account);
		return new ResponseEntity<ResponseStructure<Account>>(responseStructure, HttpStatus.CREATED);
	}

	// update name
	public ResponseEntity<ResponseStructure<String>> updateName(int accountNumber, String name) {
		String recieved = accountDao.updateName(accountNumber, name);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setData(recieved);
		responseStructure.setMessage("Success");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

	// update phone
	public ResponseEntity<ResponseStructure<String>> updatePhone(int accountNumber, long phone) {
		String recieved = accountDao.updatePhone(accountNumber, phone);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setData(recieved);
		responseStructure.setMessage("Success");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);

	}

	// get account
	public ResponseEntity<ResponseStructure<Account>> getAccountByAccountNumber(int accountNumber) {
		Account account = accountDao.getAccountByAccountNumber(accountNumber);
		ResponseStructure<Account> responseStructure = new ResponseStructure<>();
		responseStructure.setData(account);
		responseStructure.setMessage("Success");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Account>>(responseStructure, HttpStatus.OK);
	}

	// get all accounts
	public ResponseEntity<ResponseStructure<List<Account>>> getAllAccountsByBranch(int branchId) {
		List<Account> accounts = accountDao.getAllAccountsByBranch(branchId);
		ResponseStructure<List<Account>> responseStructure = new ResponseStructure<>();
		responseStructure.setData(accounts);
		responseStructure.setMessage("Success");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Account>>>(responseStructure, HttpStatus.OK);
	}

	// get branch by account number
	public ResponseEntity<ResponseStructure<Branch>> getBranchByAccount(long accountNumber) {
		Branch branch = accountDao.getBranchByAccount(accountNumber);
		if (branch != null) {
			ResponseStructure<Branch> responseStructure = new ResponseStructure<>();
			responseStructure.setData(branch);
			responseStructure.setMessage("Success");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Branch>>(responseStructure, HttpStatus.OK);
		} else {
			throw new BranchNotFoundException("Branch Does not exist");
		}
	}

	// count total number of accounts
	public ResponseEntity<ResponseStructure<Integer>> getCountBranchAccounts(int branchId) {
		int count = accountDao.getCountBranchAccounts(branchId);
		ResponseStructure<Integer> responseStructure = new ResponseStructure<>();
		responseStructure.setData(count);
		responseStructure.setMessage("Success");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Integer>>(responseStructure, HttpStatus.OK);
	}

	// delete account
	public ResponseEntity<ResponseStructure<String>> deleteAccount(int accountNumber) {
		String recieved = accountDao.deleteAccount(accountNumber);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setData(recieved);
		responseStructure.setMessage("Success");
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

	// update balance
	public String updateBalance(int accountNumber, double balance) {
		return accountDao.updateBalance(accountNumber, balance);
	}

	// transfer amount
	public ResponseEntity<ResponseStructure<Statement>> transferAmount(int fromAccount, int toAccount, double amount) {
		Statement response = accountDao.transferAmount(fromAccount, toAccount, amount);
		ResponseStructure<Statement> responseStructure = new ResponseStructure<>();
		responseStructure.setData(response);
		responseStructure.setMessage("success");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Statement>>(responseStructure, HttpStatus.OK);
	}

	@Autowired
	private StatementDao dao;

	// get all the statements
	public ResponseEntity<ResponseStructure<List<Statement>>> getAllStatements(int accountNumber) {
		List<Statement> statements = dao.getAllStatements(accountNumber);
		ResponseStructure<List<Statement>> responseStructure = new ResponseStructure<>();
		responseStructure.setData(statements);
		responseStructure.setMessage("success");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Statement>>>(responseStructure, HttpStatus.OK);
	}
}
