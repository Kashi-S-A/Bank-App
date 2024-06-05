package com.BankingApplication.BankApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.BankingApplication.BankApplication.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	@Query("SELECT e FROM Employee e WHERE e.employeeId=:employeeId")
	public Employee findById(String employeeId);

}
