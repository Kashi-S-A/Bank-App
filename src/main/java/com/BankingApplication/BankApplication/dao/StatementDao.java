package com.BankingApplication.BankApplication.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.BankingApplication.BankApplication.entity.Statement;
import com.BankingApplication.BankApplication.exception.StatementNotFoundException;
import com.BankingApplication.BankApplication.repository.StatementRespository;

@Repository
public class StatementDao {

	@Autowired
	private StatementRespository statementRespository;
	
	//save
	public Statement saveStatement(Statement statement) {
		return statementRespository.save(statement);
	}
	
	//get
	public Optional<Statement> getStatementById(int statementId) {
		return statementRespository.findById(statementId);
	}
	
	//get all
	public List<Statement> getAllStatements(int accountNumber){
		return statementRespository.getAllStatements(accountNumber);
	}
	
}
