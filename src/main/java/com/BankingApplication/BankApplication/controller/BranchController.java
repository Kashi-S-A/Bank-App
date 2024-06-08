package com.BankingApplication.BankApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BankingApplication.BankApplication.entity.Branch;
import com.BankingApplication.BankApplication.response.ResponseStructure;
import com.BankingApplication.BankApplication.service.BranchService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class BranchController {

	@Autowired
	private BranchService branchService;

//	//save
//	@PostMapping("/savebranch")
//	public ResponseEntity<ResponseStructure<Branch>> saveBranch(@RequestBody Branch branch) {
//		return branchService.saveBranch(branch);
//	}

	// update
	@Operation(description = "Send branch Id and Branch Name if Branch Exist its name will be updated as the Name you pass", summary = "You can update Branch Name")
	@PutMapping("/updatebranch/branchId/{branchId}")
	public ResponseEntity<ResponseStructure<String>> updateBranch(@PathVariable int branchId,
			@RequestParam String branchName) {
		return branchService.updateBranch(branchId, branchName);
	}

	// get by id
	@Operation(summary = "Fetch Branch Details", description = "You need to pass Branch id to Get the details if branch exist")
	@GetMapping("/getbranch/{branchId}")
	public ResponseEntity<ResponseStructure<Branch>> getBranchById(@PathVariable int branchId) {
		return branchService.getBranchById(branchId);
	}

	// get all
	@Operation(summary = "Fetch all Branch Detials", description = "You can fetch all the Branch detials without passing any inputs")
	@GetMapping("/getallbranchs")
	public ResponseEntity<ResponseStructure<List<Branch>>> getAllBranchs() {
		return branchService.getAllBranchs();
	}

	// get by bank id
	@Operation(summary = "Fetch Branch By Using Bank Id", description = "You can fetch the all branchs which associated with the Bank whose id you are passing ")
	@GetMapping("/getbranchbybankid/{bankId}")
	public ResponseEntity<ResponseStructure<List<Branch>>> getBranchsByBankId(@PathVariable int bankId) {
		return branchService.getAllBranchsByBankId(bankId);
	}

	// delete
	@Operation(summary = "Delete Branch Detials", description = "You can delete Branch details everywhere in db by passing Branch Id")
	@DeleteMapping("/deletebranch/{branchId}")
	public ResponseEntity<ResponseStructure<String>> deleteBranchById(@PathVariable int branchId) {
		return branchService.deleteBranchById(branchId);
	}

	/*--------------------------------------------------------*/

//	//assign existing branch to the bank
//	@PostMapping("/assignbankbranch/bankId/{bankId}")
//	public ResponseEntity<ResponseStructure<String>> assignBankBranch(@PathVariable int bankId,@RequestParam int branchId) {
//		return branchService.assignBankBranch(bankId, branchId);
//	}

	// assign new branch to the bank
	@Operation(summary = "Create new branch to the bank", description = "Create new branch and assign that branch to the bank, you need to pass bankId to which you want to assign a branch and that branch detials")
	@PostMapping("/assignnewbranch")
	public ResponseEntity<?> assingNewBranch(@RequestParam int bankId, @RequestBody Branch branch) {
		return branchService.assignNewBranch(bankId, branch);
	}

//	//updating bank branch
//	@PutMapping("/updatebankbranch")
//	public ResponseEntity<ResponseStructure<String>> updateBankBranch(@RequestParam int bankId,@RequestBody Branch branch) {
//		return branchService.updateBankBranch(bankId, branch);
//	}
//	
//	//delete bank branch
//	@DeleteMapping("/deletebankbranch/bankId/{bankId}")
//	public ResponseEntity<ResponseStructure<String>> deleteBankBranch(@PathVariable int bankId,@RequestParam int branchId) {
//		return branchService.deleteBankBranch(branchId, bankId);
//	}
}
