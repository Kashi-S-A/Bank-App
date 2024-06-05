package com.BankingApplication.BankApplication.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.BankingApplication.BankApplication.entity.Account;
import com.BankingApplication.BankApplication.entity.Branch;
import com.BankingApplication.BankApplication.exception.AccountNotFoundException;
import com.BankingApplication.BankApplication.exception.BranchNotFoundException;
import com.BankingApplication.BankApplication.repository.AccountRepository;

@Repository
public class AccountDao {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private BranchDao branchdao;

	// save account
	public Account saveAccount(Account account) {
		return accountRepository.save(account);
	}

	// update
	public String updateName(int accountNumber, String name) {
		Account account = accountRepository.findById(accountNumber)
				.orElseThrow(() -> new com.BankingApplication.BankApplication.exception.AccountNotFoundException(
						"account does not exists"));
		account.setAccountHolderName(name);
		accountRepository.save(account);
		return "account holder name is updated";
	}

	// update phone
	public String updatePhone(int accountNumber, long phoneNumber) {
		Account account = accountRepository.findById(accountNumber)
				.orElseThrow(() -> new AccountNotFoundException("Account does not exist"));
		account.setPhoneNumber(phoneNumber);
		accountRepository.save(account);
		return "account Phone Number is updated";
	}

	// get account by id
	public Account getAccountByAccountNumber(int accountNumber) {
		Account account = accountRepository.findById(accountNumber)
				.orElseThrow(() -> new AccountNotFoundException("account does not exist"));
		return account;
	}

	// get all accounts
	public List<Account> getAllAccountsByBranch(int branchId) {
		Branch branch = branchdao.getBranchById(branchId)
				.orElseThrow(() -> new BranchNotFoundException("Branch does not exist"));
		return branch.getAccounts();
	}

	// get branch by account number
	public Branch getBranchByAccount(long accountNumber) {
		Account account = accountRepository.getAccountByAccountNumber(accountNumber);
		return account.getBranch();
	}

	// count of accounts in a particular branch
	public int getCountBranchAccounts(int branchId) {
		Branch branch = branchdao.getBranchById(branchId)
				.orElseThrow(() -> new BranchNotFoundException("Branch does not exist"));
		return branch.getAccounts().size();
	}

	// delete account by id
	public String deleteAccount(int accountNumber) {
		Account account = accountRepository.findById(accountNumber)
				.orElseThrow(() -> new AccountNotFoundException("account does not exist"));
		Branch branch = account.getBranch();
		List<Account> accounts = branch.getAccounts();
		accounts.remove(account);
		branch.setAccounts(accounts);
		// branchService.updateBranch(branch);
		account.setBranch(null);
		accountRepository.delete(account);
		return "account is deleted";

	}
}
