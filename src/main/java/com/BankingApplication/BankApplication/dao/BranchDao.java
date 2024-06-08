package com.BankingApplication.BankApplication.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.BankingApplication.BankApplication.entity.Account;
import com.BankingApplication.BankApplication.entity.Bank;
import com.BankingApplication.BankApplication.entity.Branch;
import com.BankingApplication.BankApplication.exception.BranchNotFoundException;
import com.BankingApplication.BankApplication.repository.AccountRepository;
import com.BankingApplication.BankApplication.repository.BankRepository;
import com.BankingApplication.BankApplication.repository.BranchRepository;

@Repository
public class BranchDao {

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private AccountRepository accountRepository;

	// save branch
	public Branch saveBranch(Branch branch) {
		return branchRepository.save(branch);
	}

	// update branch
	public String updateBranch(int branchId,String branchName) {
		Branch branch = branchRepository.findById(branchId)
				.orElseThrow(() -> new BranchNotFoundException("Branch Does not exist"));
		branch.setBranchName(branchName);
		branchRepository.save(branch);
		return "branch updated";
	}

	// get by id
	public Optional<Branch> getBranchById(int branchId) {
		return branchRepository.findById(branchId);
	}

	// get all branches
	public List<Branch> getAllBranches() {
		return branchRepository.findAll();
	}

	// delete branch by id
	public String deleteBranchById(int branchId) {
		// Branch branch=branchRepository.getById(branchId);
		Branch branch = branchRepository.findById(branchId).orElseThrow(()->new BranchNotFoundException("Branch Does not exist"));
			// breaking the association between bank and branch to delete the branch
			// removing branch from the bank
			Bank bank = branch.getBank();
			if (bank != null) {
				List<Branch> branchs = bank.getBranchs();
				branchs.remove(branch);
				bank.setBranchs(branchs);
				bankRepository.save(bank);
			}
            List<Account> accounts=branch.getAccounts();
            if (!accounts.isEmpty()) {
				for (Account account : accounts) {
					account.setBranch(null);
					accountRepository.save(account);
				}
			}
			// removing bank from the branch
			branch.setBank(null);
			branch.setEmployee(null);
			branch.setBranchAddress(null);
			branch.setAccounts(null);
			branchRepository.save(branch);
			branchRepository.delete(branch);
			return "branch deleted";
		} 

	// get all branches by bank id
	public List<Branch> getAllBranchsByBankId(int bankId) {
		return branchRepository.getAllBranchsByBankId(bankId);
	}

	// get branch by employee id
	public Branch getBranchByEmployeeId(int employeeId) {
		return branchRepository.getBranchByEmployeeId(employeeId);
	}

}
