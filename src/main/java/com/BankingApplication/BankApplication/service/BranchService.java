package com.BankingApplication.BankApplication.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.BankingApplication.BankApplication.dao.BankDao;
import com.BankingApplication.BankApplication.dao.BranchDao;
import com.BankingApplication.BankApplication.entity.Bank;
import com.BankingApplication.BankApplication.entity.Branch;
import com.BankingApplication.BankApplication.exception.BankNotFound;
import com.BankingApplication.BankApplication.exception.BranchNotFoundException;
import com.BankingApplication.BankApplication.repository.BankRepository;
import com.BankingApplication.BankApplication.repository.BranchRepository;
import com.BankingApplication.BankApplication.response.ResponseStructure;

@Service
public class BranchService {

	@Autowired
	private BranchDao branchDao;

	@Autowired
	private BankDao bankDao;

	// save
//	public ResponseEntity<ResponseStructure<Branch>> saveBranch(Branch branch) {
//		Branch branch1 = branchDao.saveBranch(branch);
//		if (branch1 != null) {
//			ResponseStructure<Branch> responseStructure = new ResponseStructure<>();
//			responseStructure.setData(branch1);
//			responseStructure.setMessage("success");
//			responseStructure.setStatusCode(HttpStatus.CREATED.value());
//			return new ResponseEntity<ResponseStructure<Branch>>(responseStructure, HttpStatus.CREATED);
//		} else {
//			throw new BranchNotFoundException("Not saved");
//		}
//	}

	// update
	public ResponseEntity<ResponseStructure<String>> updateBranch(int branchId,String branchName) {
		String recieved = branchDao.updateBranch(branchId,branchName);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setData(recieved);
		responseStructure.setMessage("success");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);

	}

	// getbyid
	public ResponseEntity<ResponseStructure<Branch>> getBranchById(int branchId) {
		Branch branch = branchDao.getBranchById(branchId)
				.orElseThrow(() -> new BranchNotFoundException("Branch does not exist"));
		if (branch != null) {
			ResponseStructure<Branch> responseStructure = new ResponseStructure<>();
			responseStructure.setData(branch);
			responseStructure.setMessage("success");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Branch>>(responseStructure, HttpStatus.OK);
		} else {
			throw new BranchNotFoundException("Branch Does not exist");
		}
	}

	// get all
	public ResponseEntity<ResponseStructure<List<Branch>>> getAllBranchs() {
		List<Branch> branchs = branchDao.getAllBranches();
		if (!branchs.isEmpty()) {
			ResponseStructure<List<Branch>> responseStructure = new ResponseStructure<>();
			responseStructure.setData(branchs);
			responseStructure.setMessage("success");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Branch>>>(responseStructure, HttpStatus.OK);
		} else {
			throw new BranchNotFoundException("No branch records are found");
		}
	}

	// delete
	public ResponseEntity<ResponseStructure<String>> deleteBranchById(int branchId) {
		String recieved = branchDao.deleteBranchById(branchId);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setData(recieved);
		responseStructure.setMessage("Success");
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

	// get by bank id
	public ResponseEntity<ResponseStructure<List<Branch>>> getAllBranchsByBankId(int bankId) {
		Bank bank = bankDao.getBankById(bankId)
				.orElseThrow(() -> new BankNotFound("Bank not found to gett all branches"));
		List<Branch> branchs = branchDao.getAllBranchsByBankId(bankId);
		ResponseStructure<List<Branch>> responseStructure = new ResponseStructure<>();
		responseStructure.setData(branchs);
		responseStructure.setMessage("success");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Branch>>>(responseStructure, HttpStatus.OK);
	}

	/*------------------------------------------------------------*/

	@Autowired
	private BankRepository bankRepository;

	@Autowired
	private BranchRepository branchRepository;

	// assigning branch to the bank
//	public ResponseEntity<ResponseStructure<String>> assignBankBranch(int bankId, int branchId) {
//		Branch branch = branchRepository.findById(branchId)
//				.orElseThrow(() -> new BranchNotFoundException("Branch does not found"));
//
//		Bank bank = bankRepository.findById(bankId)
//				.orElseThrow(() -> new BranchNotFoundException("Bank does not exist"));
//
//		/*
//		 * Branch branch=branchDao.getBranchByEmployeeId(employeeId); if (branch !=
//		 * null) { return
//		 * "cannot assign this employee bcz he is working in the branch with id " +
//		 * branch.getBranchId(); }
//		 */
//		// cz single employee cannot work for more than one entity
////		List<Branch> branchs =  getAllBranchs();
////		if (!(branchs.contains(branch))) {
////			branchs.add(branch);
//// bank.setBranchs(branchs);
//// }
//		branch.setBank(bank);
//		bank.getBranchs().add(branch);
//		ResponseStructure<String> responseStructure = new ResponseStructure<>();
//		try {
//			bankRepository.save(bank);
//			branchRepository.save(branch);
//			responseStructure.setData("branch is assigned to the bank");
//			responseStructure.setMessage("Success");
//			responseStructure.setStatusCode(HttpStatus.OK.value());
//		} catch (Exception e) {
//			throw new BranchNotFoundException(
//					"this branch cannot be assigned to any of the bank cz it is already assigned to someother banks");
//		}
//		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
//
//	}

	// assign new Branch to the bank
	public ResponseEntity<?> assignNewBranch(int bankId, Branch branch) {
		Bank bank = bankRepository.findById(bankId)
				.orElseThrow(() -> new BranchNotFoundException("Bank does not exist"));
		List<Branch> branchs = bank.getBranchs();
		System.out.println(branchs);
		boolean ispresent=false;
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		if (!branchs.isEmpty()) {
			for (Branch branch2 : branchs) {
					if (!(branch2.getBranchName().equals(branch.getBranchName()))) {
						ResponseStructure<Branch> responseStructure1 = new ResponseStructure<>();
						branch.setBank(bank);
						branchs.add(branch);
						branchDao.saveBranch(branch);
						ispresent=true;
						responseStructure1.setData(branch);
						responseStructure1.setMessage("Success");
						responseStructure1.setStatusCode(HttpStatus.OK.value());
						return new ResponseEntity<ResponseStructure<Branch>>(responseStructure1,HttpStatus.OK);
					}
				}
		} else {
			branch.setBank(bank);
			branchs.add(branch);
			branchDao.saveBranch(branch);
			responseStructure.setData("new branch is assigned ");
			responseStructure.setMessage("Success");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
		}
		if (!ispresent) {
			responseStructure.setData(" this branch is already present");
			responseStructure.setMessage("failed");
			responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
		}
		responseStructure.setData("new branch is not assigned to the bank");
		responseStructure.setMessage("failed");
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);

	}

	// update branch in the bank
//	public ResponseEntity<ResponseStructure<String>> updateBankBranch(int bankId, Branch branch) {
//		Branch branch1 = branchRepository.findById(branch.getBranchId())
//				.orElseThrow(() -> new BranchNotFoundException("Branch does not found"));
//
//		Bank bank = bankRepository.findById(bankId)
//				.orElseThrow(() -> new BranchNotFoundException("Bank does not exist"));
//
//		ResponseStructure<String> responseStructure = new ResponseStructure<>();
//		if (branch1.getBank().getBankId() == bankId) {
//			branch.setBank(bank);
//			//updateBranch(branch);
//			responseStructure.setData("branch record got update in the bank");
//			responseStructure.setMessage("success");
//			responseStructure.setStatusCode(HttpStatus.OK.value());
//			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
//		} else {
//			throw new BranchNotFoundException("bank does not exist");
//		}
//	}

	// delete branch from the bank
//	public ResponseEntity<ResponseStructure<String>> deleteBankBranch(int branchId, int bankId) {
//		Branch branch = branchRepository.findById(branchId)
//				.orElseThrow(() -> new BranchNotFoundException("Branch does not found"));
//
//		Bank bank = bankRepository.findById(bankId)
//				.orElseThrow(() -> new BranchNotFoundException("Bank does not exist"));
//		ResponseStructure<String> responseStructure = new ResponseStructure<>();
//		if (branch.getBank() != null && branch.getBank().getBankId() == bank.getBankId()) {
//
//			deleteBranchById(branchId);
//			responseStructure.setData("branch is removed from the bank");
//			responseStructure.setMessage("success");
//			responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
//			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NO_CONTENT);
//		} else {
//			responseStructure.setData("branch is not associated with this bank");
//			responseStructure.setMessage("failed");
//			responseStructure.setStatusCode(HttpStatus.OK.value());
//			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
//		}
//	}

}
