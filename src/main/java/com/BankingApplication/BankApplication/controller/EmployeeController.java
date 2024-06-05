package com.BankingApplication.BankApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BankingApplication.BankApplication.entity.Employee;
import com.BankingApplication.BankApplication.response.ResponseStructure;
import com.BankingApplication.BankApplication.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// save
	@PostMapping("/saveemployee")
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}

	// update
	@PutMapping("/updateemployee")
	public ResponseEntity<ResponseStructure<String>> updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}

	// update employee name by id
	@PatchMapping("/updateemployeename/employeeId/{employeeId}")
	public ResponseEntity<ResponseStructure<String>> updateEmployeeName(@PathVariable int employeeId, @RequestParam String employeeName) {
		return employeeService.updateEmployeeName(employeeId, employeeName);
	}

	// update employee email by id
	@PatchMapping("/updateemployeeemail/employeeId/{employeeId}")
	public ResponseEntity<ResponseStructure<String>> updateEmployeeEmail(@PathVariable int employeeId, @RequestParam String employeeEmail) {
		return employeeService.updateEmployeeEmail(employeeId, employeeEmail);
	}

	// update employee phone by id
	@PatchMapping("/updateemployeephone/employeeId/{employeeId}")
	public ResponseEntity<ResponseStructure<String>> updateEmployeePhone(@PathVariable int employeeId, @RequestParam long employeePhone) {
		return employeeService.updateEmployeePhone(employeeId, employeePhone);
	}

	// get emp by id
	@GetMapping("/getemployee/{employeeId}")
	public ResponseEntity<ResponseStructure<Employee>> getEmployeeById(@PathVariable String employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}

	// get all employees
	@GetMapping("/getallemployees")
	public ResponseEntity<ResponseStructure<List<Employee>>> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	// delete
	@DeleteMapping("/deleteemployee/{employeeId}")
	public ResponseEntity<ResponseStructure<String>> deleteEmployeeById(@PathVariable int employeeId) {
		return employeeService.deleteEmployee(employeeId);
	}

	/*--------------------------------------------------------------*/

	// assigning existing employee to the bank
	@PostMapping("/assignbank/employeeId/{employeeId}")
	public ResponseEntity<ResponseStructure<String>> assignBank(@PathVariable int employeeId, @RequestParam int bankId) {
		return employeeService.assignEmpToBankById(employeeId, bankId);
	}

	// assigning new employee to the bank
	@PostMapping("/assignnewbankemployee/bankId/{bankId}")
	public ResponseEntity<ResponseStructure<String>> assigNewBankEmployee(@PathVariable int bankId, @RequestBody Employee employee) {
		return employeeService.assignNewBankEmployee(bankId, employee);
	}

	// update employee in the bank//put is to update multiple attributes
	// if we want to update particular attribute single we need to make us of
	// pathmapping
	@PutMapping("/updatebankemployee/bankId/{bankId}")
	public ResponseEntity<ResponseStructure<String>> updateBankEmployee(@PathVariable int bankId, @RequestBody Employee employee) {
		return employeeService.updateBankEmployee(bankId, employee);
	}

	// delete employee from the bank and employee table
	@DeleteMapping("/deletebankemployee/employeeId/{employeeId}")
	public ResponseEntity<ResponseStructure<String>> deleteBankEmployee(@PathVariable int employeeId, @RequestParam int bankId) {
		return employeeService.deleteBankEmployee(employeeId, bankId);
	}

	/*---------------------------------------------------------------------*/
	// assigning employee to the branch

	// assigning existing employee to the branch
	@PostMapping("/assignbranch/employeeId/{employeeId}")
	public ResponseEntity<ResponseStructure<String>> assignBranch(@PathVariable int employeeId, @RequestParam int branchId) {
		return employeeService.assignEmpToBranch(employeeId, branchId);
	}

	// assigning new employee to the branch
	@PostMapping("/assignnewbranchemployee")
	public ResponseEntity<ResponseStructure<String>> assigNewBranchEmployee(@RequestParam int branchId, @RequestBody Employee employee) {
		return employeeService.assignNewEmployeeToBranch(branchId, employee);
	}

	// update employee in the branch//put is to update multiple attributes
	// if we want to update particular attribute single we need to make us of
	// patchmapping
	@PutMapping("/updatebranchemployee")
	public ResponseEntity<ResponseStructure<String>> updateBranchEmployee(@RequestParam int branchId, @RequestBody Employee employee) {
		return employeeService.updateBranchEmployee(branchId, employee);
	}

	// delete employee from the branch and employee table
	@DeleteMapping("/deletebranchemployee/employeeId/{employeeId}")
	public ResponseEntity<ResponseStructure<String>> deleteBranchEmployee(@PathVariable int employeeId, @RequestParam int branchId) {
		return employeeService.deleteBranchEmployee(employeeId, branchId);
	}
}
