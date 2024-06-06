package com.BankingApplication.BankApplication.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.BankingApplication.BankApplication.entity.Bank;
import com.BankingApplication.BankApplication.exception.BankNotFound;
import com.BankingApplication.BankApplication.repository.BankRepository;

@Repository
public class BankDao {

	@Autowired
	private BankRepository bankRepository;

	// save bank
	public Bank saveBank(Bank bank) {
		return bankRepository.save(bank);
	}

	// update bank
	public String updateBank(Bank bank) {
		Bank bank1 = bankRepository.findById(bank.getBankId())
				.orElseThrow(() -> new BankNotFound("bank does not exist"));
        bank.setBankAddress(bank1.getBankAddress());
		bankRepository.save(bank);
		return "Bank updated";
	}

	// get bank by id
	public Optional<Bank> getBankById(int bankId) {
		return bankRepository.findById(bankId);
	}

	// get all banks
	public List<Bank> getAllBanks() {
		return bankRepository.findAll();
	}

	// get bank by employee id
	public Bank getBankByEmployeeId(int employeeId) {
		return bankRepository.getBankByEmployeeId(employeeId);
	}

	// delete bank by id
	public String deleteBankById(int bankId) {
		Bank bank = bankRepository.findById(bankId).orElseThrow(() -> new BankNotFound("Bank not found"));
		bankRepository.delete(bank);
		return "Bank is deleted";
	}

}
