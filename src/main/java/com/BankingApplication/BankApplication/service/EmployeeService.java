package com.BankingApplication.BankApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.BankingApplication.BankApplication.dao.BankDao;
import com.BankingApplication.BankApplication.dao.BranchDao;
import com.BankingApplication.BankApplication.dao.EmployeeDao;
import com.BankingApplication.BankApplication.entity.Bank;
import com.BankingApplication.BankApplication.entity.Branch;
import com.BankingApplication.BankApplication.entity.Employee;
import com.BankingApplication.BankApplication.exception.BankNotFound;
import com.BankingApplication.BankApplication.exception.BranchNotFoundException;
import com.BankingApplication.BankApplication.exception.EmployeeNotFoundException;
import com.BankingApplication.BankApplication.repository.BankRepository;
import com.BankingApplication.BankApplication.repository.BranchRepository;
import com.BankingApplication.BankApplication.repository.EmployeeRepository;
import com.BankingApplication.BankApplication.response.ResponseStructure;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private BankDao bankDao;

	@Autowired
	private BranchDao branchDao;

	// save
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee employee) {
		Employee employee1 = employeeDao.saveEmployee(employee);
		if (employee1 != null) {
			ResponseStructure<Employee> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("success");
			responseStructure.setData(employee1);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Employee>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new EmployeeNotFoundException("employee is not found");
		}
	}

	// update
	public ResponseEntity<ResponseStructure<String>> updateEmployee(Employee employee) {
		String recieved = employeeDao.updateEmployee(employee);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(recieved);
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

	// update emp name
	public ResponseEntity<ResponseStructure<String>> updateEmployeeName(int employeeId, String employeeName) {
		String recieved = employeeDao.updateEmployeeName(employeeId, employeeName);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(recieved);
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);

	}

	// update emp email
	public ResponseEntity<ResponseStructure<String>> updateEmployeeEmail(int employeeId, String employeeEmail) {
		String recieved = employeeDao.updateEmployeeEmail(employeeId, employeeEmail);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(recieved);
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);

	}

	// update emp phone
	public ResponseEntity<ResponseStructure<String>> updateEmployeePhone(int employeeId, long employeePhone) {
		String recieved = employeeDao.updateEmployeePhone(employeeId, employeePhone);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(recieved);
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

	// get by id
	public ResponseEntity<ResponseStructure<Employee>> getEmployeeById(String employeeId) {
		Employee employee = employeeDao.getEmployeeById(employeeId);
		if (employee != null) {
			ResponseStructure<Employee> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("success");
			responseStructure.setData(employee);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Employee>>(responseStructure, HttpStatus.OK);
		} else {
			throw new EmployeeNotFoundException("employee is not found");
		}
	}

	// get all
	public ResponseEntity<ResponseStructure<List<Employee>>> getAllEmployees() {
		List<Employee> employees = employeeDao.getAllEmployees();
		if (employees != null) {
			ResponseStructure<List<Employee>> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("Success");
			responseStructure.setData(employees);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Employee>>>(responseStructure, HttpStatus.OK);
		} else {
			throw new EmployeeNotFoundException("employee is not found");
		}
	}

	// delete-->No_content
	public ResponseEntity<ResponseStructure<String>> deleteEmployee(int employeeId) {
		String recieved = employeeDao.deleteEmployeeById(employeeId);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(recieved);
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NO_CONTENT);

	}

	/*------------------------------------------------------------------*/

	// assigning employee to the bank
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private BankRepository bankRepository;

	// assign existing employee to the bank
	public ResponseEntity<ResponseStructure<String>> assignEmpToBankById(int employeeId, int bankId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with this ID does not exist"));

		Bank bank = bankRepository.findById(bankId).orElseThrow(() -> new BankNotFound("This bank does not exist"));

		/*
		 * Branch branch=branchDao.getBranchByEmployeeId(employeeId); if (branch !=
		 * null) { return
		 * "cannot assign this employee bcz he is working in the branch with id " +
		 * branch.getBranchId(); }
		 */
		// cz single employee cannot work for more than one entity
		employee.setBank(bank);
		employee.setBranch(null);
		bank.setEmployee(employee);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		try {
			responseStructure.setData("employee assigned to the bank " + bank.getBankId());
			responseStructure.setMessage("Success");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			employeeRepository.save(employee);
			bankRepository.save(bank);
		} catch (Exception e) {
			throw new EmployeeNotFoundException(
					"this employee cannot be assigned to any of the bank cz he is working for some other banks");
		}
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

	// assign new employee to the bank
	public ResponseEntity<ResponseStructure<String>> assignNewBankEmployee(int bankId, Employee employee) {
		Bank bank = bankRepository.findById(bankId).orElseThrow(() -> new BankNotFound("Bank does not found"));
		bank.setEmployee(employee);
		bankRepository.save(bank);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setData("new emp is assigned to the bank with id " + bank.getBankId());
		responseStructure.setMessage("success");
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

	// update employee in the bank
	public ResponseEntity<ResponseStructure<String>> updateBankEmployee(int bankId, Employee employee) {
		Employee employee1 = employeeRepository.findById(employee.getEmployeeId())
				.orElseThrow(() -> new EmployeeNotFoundException("employee does not exist"));

		Bank bank = bankRepository.findById(bankId).orElseThrow(() -> new BankNotFound("Bank does not found"));
		bank.setEmployee(employee);
		bankRepository.save(bank);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setMessage("Success");
		responseStructure.setData("employee record got updated in the bank ");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

	// delete employee from the bank
	public ResponseEntity<ResponseStructure<String>> deleteBankEmployee(int employeeId, int bankId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with this ID does not exist"));

		Bank bank = bankRepository.findById(bankId).orElseThrow(() -> new BankNotFound("Bank does not found"));

		bank.setEmployee(null);
		bankRepository.save(bank);
		deleteEmployee(employee.getEmployeeId());

		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setMessage("Success");
		responseStructure.setData("employee record got deleted");
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NO_CONTENT);
	}

	/*--------------------------------------------------------------------*/

	@Autowired
	private BranchRepository branchRepository;

	// assign existing employee to the branch
	public ResponseEntity<ResponseStructure<String>> assignEmpToBranch(int employeeId, int branchId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with this ID does not exist"));

		Branch branch = branchRepository.findById(branchId)
				.orElseThrow(() -> new BranchNotFoundException("branch does not found"));

		/*
		 * Bank bank=employee.getBank(); System.out.println(bank); if (bank!=null) {
		 * return "this employee is working in the bank with id "+bank.getBankId(); }
		 */
		/*
		 * Bank bank = bankDao.getBankByEmployeeId(employeeId); if (bank != null) {
		 * return "cannot assign this employee bcz he is working in the bank with id " +
		 * bank.getBankId(); }
		 */

		employee.setBranch(branch);
		employee.setBank(null);
		branch.setEmployee(employee);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		try {
			employeeRepository.save(employee);
			branchRepository.save(branch);
			
			responseStructure.setMessage("Success");
			responseStructure.setData("employee is assigned to the Branch");
			responseStructure.setStatusCode(HttpStatus.OK.value());
		} catch (Exception e) {
			throw new EmployeeNotFoundException("this employee cannot be assigned to any of the branchs cz he is working for some other branches");
		}
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

	// assign new employee to the branch
	public ResponseEntity<ResponseStructure<String>> assignNewEmployeeToBranch(int branchId, Employee employee) {
		Branch branch = branchRepository.findById(branchId).orElseThrow(()->new BranchNotFoundException("branch does not exist"));
			branch.setEmployee(employee);
			branchRepository.save(branch);
			ResponseStructure<String> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("Success");
			responseStructure.setData("new employee is assigned to the branch");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

	// update employee in the branch
	public ResponseEntity<ResponseStructure<String>> updateBranchEmployee(int branchId, Employee employee) {
		Employee employee1 = employeeRepository.findById(employee.getEmployeeId())
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with this ID does not exist"));

		Branch branch = branchRepository.findById(branchId)
				.orElseThrow(() -> new BranchNotFoundException("branch does not found"));

			branch.setEmployee(employee);
			branchRepository.save(branch);
			ResponseStructure<String> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("Success");
			responseStructure.setData("Branch employee is updated");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

	// delete employee from the bank
	public ResponseEntity<ResponseStructure<String>> deleteBranchEmployee(int employeeId, int branchId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with this ID does not exist"));

		Branch branch = branchRepository.findById(branchId)
				.orElseThrow(() -> new BranchNotFoundException("branch does not found"));

			branch.setEmployee(null);
			branchRepository.save(branch);
			deleteEmployee(employee.getEmployeeId());
			ResponseStructure<String> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("Success");
			responseStructure.setData("Branch employee is removed");
			responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NO_CONTENT);
	
	}
}
