package com.BankingApplication.BankApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.BankingApplication.BankApplication.entity.Statement;

public interface StatementRespository extends JpaRepository<Statement, Integer>{

	@Query("SELECT s FROM Statement s WHERE s.account.accountNumber=:accountNumber")
	public List<Statement> getAllStatements(long accountNumber);
}
