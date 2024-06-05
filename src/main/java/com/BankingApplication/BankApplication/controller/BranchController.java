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

@RestController
public class BranchController {

	@Autowired
	private BranchService branchService;
	
	//save
	@PostMapping("/savebranch")
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(@RequestBody Branch branch) {
		return branchService.saveBranch(branch);
	}
	
	//update
	@PutMapping("/updatebranch")
	public ResponseEntity<ResponseStructure<String>> updateBranch(@RequestBody Branch branch) {
		return branchService.updateBranch(branch);
	}
	
	//get by id
	@GetMapping("/getbranch/{branchId}")
	public ResponseEntity<ResponseStructure<Branch>> getBranchById(@PathVariable int branchId) {
		return branchService.getBranchById(branchId);
	}
	
	//get all 
	@GetMapping("/getallbranchs")
	public ResponseEntity<ResponseStructure<List<Branch>>> getAllBranchs(){
		return branchService.getAllBranchs();
	}
	
	//get by bank id
	@GetMapping("/getbranchbybankid/{bankId}")
	public ResponseEntity<ResponseStructure<List<Branch>>> getBranchsByBankId(@PathVariable int bankId){
		return branchService.getAllBranchsByBankId(bankId);
	}
	
	//delete
	@DeleteMapping("/deletebranch/{branchId}")
	public ResponseEntity<ResponseStructure<String>> deleteBranchById(@PathVariable int branchId) {
		return branchService.deleteBranchById(branchId);
	}
	
	/*--------------------------------------------------------*/
	
	//assign existing branch to the bank
	@PostMapping("/assignbankbranch/bankId/{bankId}")
	public ResponseEntity<ResponseStructure<String>> assignBankBranch(@PathVariable int bankId,@RequestParam int branchId) {
		return branchService.assignBankBranch(bankId, branchId);
	}
	
	//assign new branch to the bank
	@PostMapping("/assignnewbranch")
	public ResponseEntity<ResponseStructure<String>> assingNewBranch(@RequestParam int bankId,@RequestBody Branch branch) {
		return branchService.assignNewBranch(bankId, branch);
	}
	
	//updating bank branch
	@PutMapping("/updatebankbranch")
	public ResponseEntity<ResponseStructure<String>> updateBankBranch(@RequestParam int bankId,@RequestBody Branch branch) {
		return branchService.updateBankBranch(bankId, branch);
	}
	
	//delete bank branch
	@DeleteMapping("/deletebankbranch/bankId/{bankId}")
	public ResponseEntity<ResponseStructure<String>> deleteBankBranch(@PathVariable int bankId,@RequestParam int branchId) {
		return branchService.deleteBankBranch(branchId, bankId);
	}
}
