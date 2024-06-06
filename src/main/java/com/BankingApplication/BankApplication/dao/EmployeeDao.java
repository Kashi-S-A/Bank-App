package com.BankingApplication.BankApplication.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.BankingApplication.BankApplication.entity.Bank;
import com.BankingApplication.BankApplication.entity.Branch;
import com.BankingApplication.BankApplication.entity.Employee;
import com.BankingApplication.BankApplication.exception.EmployeeNotFoundException;
import com.BankingApplication.BankApplication.repository.EmployeeRepository;

@Repository
public class EmployeeDao {

	@Autowired
	private EmployeeRepository employeeRepository;

	// to save employee
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	// update employee
	public String updateEmployee(Employee employee) {
		Employee employee1 = employeeRepository.findById(employee.getEmployeeId())
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with this ID does not exist"));
		employee.setEmployeeAddress(employee1.getEmployeeAddress());
		employeeRepository.save(employee);
		return "updated successfully";
	}

	// update emp name
	public String updateEmployeeName(int employeeId, String employeeName) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with this ID deos not exist"));
		employee.setName(employeeName);
		employeeRepository.save(employee);
		return "Employee Name is updated";

	}

	// update emp address
	public String updateEmployeeEmail(int employeeId, String employeeEmail) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new EmployeeNotFoundException("Employee with this ID deos not exist"));
			employee.setEmail(employeeEmail);
			employeeRepository.save(employee);
			return "Employee Email is updated"; 
	}

	// update emp phone
	public String updateEmployeePhone(int employeeId, long employeePhone) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new EmployeeNotFoundException("Employee with this ID deos not exist"));
			employee.setPhoneNumber(employeePhone);
			employeeRepository.save(employee);
			return "Employee Phone Number is updated";
	}

	// get employee by id
	public Employee getEmployeeById(int id) {
		return employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException("employee does not exist"));
	}

	// get all the employees
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Autowired
	private BranchDao branchDao;

	@Autowired
	private BankDao bankDao;

	// delete employee
	public String deleteEmployeeById(int employeeId) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new EmployeeNotFoundException("Employee does not exist"));
		Bank bank = bankDao.getBankByEmployeeId(employeeId);
			if (bank != null) {
				bank.setEmployee(null);
				bankDao.saveBank(bank);
			}
			Branch branch = branchDao.getBranchByEmployeeId(employeeId);
			if (branch != null) {
				branch.setEmployee(null);
				branchDao.saveBranch(branch);
			}
			employeeRepository.delete(employee);
			return "employee deleted";
	}
}
