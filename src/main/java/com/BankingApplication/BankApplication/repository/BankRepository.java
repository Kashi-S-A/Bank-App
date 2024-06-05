package com.BankingApplication.BankApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.BankingApplication.BankApplication.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer>{

	@Query("SELECT b FROM Bank b WHERE b.bankId=:bankId")
	public Bank findById(String bankId);
	
	@Query("SELECT b FROM Bank b WHERE b.employee.employeeId=:employeeId")
	public Bank getBankByEmployeeId(int employeeId);

}
