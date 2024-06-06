package com.BankingApplication.BankApplication.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.BankingApplication.BankApplication.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer>{

	@Query("SELECT b FROM Branch b WHERE b.branchId=:branchId")
	public Branch getById(String branchId);
	
	@Query("SELECT b FROM Branch b WHERE b.bank.id=:bankId")
	public List<Branch> getAllBranchsByBankId(int bankId);
	
	@Query("SELECT b FROM Branch b WHERE b.employee.id=:employeeId")
	public Branch getBranchByEmployeeId(int employeeId);
	
}
