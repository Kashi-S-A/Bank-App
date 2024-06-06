package com.BankingApplication.BankApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.BankingApplication.BankApplication.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

	@Query("SELECT a FROM Account a WHERE a.accountNumber=:accountNumber")
	public Account getAccountByAccountNumber(long accountNumber);
	
}
