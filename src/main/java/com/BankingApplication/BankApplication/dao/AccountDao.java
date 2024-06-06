package com.BankingApplication.BankApplication.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.BankingApplication.BankApplication.entity.Account;
import com.BankingApplication.BankApplication.entity.Branch;
import com.BankingApplication.BankApplication.entity.DebitCard;
import com.BankingApplication.BankApplication.entity.Statement;
import com.BankingApplication.BankApplication.entity.TransactionType;
import com.BankingApplication.BankApplication.exception.AccountNotFoundException;
import com.BankingApplication.BankApplication.exception.BranchNotFoundException;
import com.BankingApplication.BankApplication.repository.AccountRepository;
import com.BankingApplication.BankApplication.repository.DebitCardRepository;

@Repository
public class AccountDao {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private BranchDao branchdao;

	@Autowired
	private DebitCardRepository cardDao;

	// save account
	public Account saveAccount(Account account) {
		return accountRepository.save(account);
	}

	// update Name
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
		if (account.getDebitCard() != null) {
			DebitCard card = account.getDebitCard();
			account.setDebitCard(null);
			card.setAccount(null);
			cardDao.save(card);
			cardDao.delete(card);
		}
		account.setBranch(null);
		saveAccount(account);
		accountRepository.delete(account);
		return "account is deleted";

	}

	// update Name
	public String updateBalance(int accountNumber, double balance) {
		Account account = accountRepository.findById(accountNumber)
				.orElseThrow(() -> new com.BankingApplication.BankApplication.exception.AccountNotFoundException(
						"account does not exists"));
		account.setBalance(balance);
		accountRepository.save(account);
		return "account balance is updated in account with account Number"+account.getAccountNumber();
	}

	@Autowired
	private StatementDao statementDao;
	
	//transfer amount to another account
	public Statement transferAmount(int fromAccountNumber,int toAccountNumber,double amount) {
		Account fromAccount=getAccountByAccountNumber(fromAccountNumber);
		
		Account toAccount=getAccountByAccountNumber(toAccountNumber);
		if (amount>0&&fromAccount.getBalance()>=amount) {
			double fromAccountBalance=fromAccount.getBalance()-amount;
			Statement fromStatement=new Statement();
			fromStatement.setAmount(amount);
			fromStatement.setAccount(fromAccount);
			fromStatement.setTransactionType(TransactionType.DEBITED);
			fromStatement.setToAccount(toAccount);
		    Statement s1=statementDao.saveStatement(fromStatement);
		    fromAccount.getStatements().add(s1);
		    fromAccount.setBalance(fromAccountBalance);
		    accountRepository.save(fromAccount);
			
			double toAccountBalance=toAccount.getBalance()+amount;
			Statement toStatement=new Statement();
			toStatement.setAccount(toAccount);
			toStatement.setAmount(amount);
			toStatement.setTransactionType(TransactionType.CREDITED);
			toStatement.setToAccount(fromAccount);
			Statement s2=statementDao.saveStatement(toStatement);
			toAccount.getStatements().add(s2);
			toAccount.setBalance(toAccountBalance);
			accountRepository.save(toAccount);
			
			return s1;
		} else {
			return null;
		}
	}
}
