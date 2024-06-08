package com.BankingApplication.BankApplication.controller;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.multipart.MultipartFile;

import com.BankingApplication.BankApplication.dao.AccountDao;
import com.BankingApplication.BankApplication.entity.Account;
import com.BankingApplication.BankApplication.entity.Branch;
import com.BankingApplication.BankApplication.entity.Statement;
import com.BankingApplication.BankApplication.repository.AccountRepository;
import com.BankingApplication.BankApplication.response.ResponseStructure;
import com.BankingApplication.BankApplication.service.AccountService;
import com.BankingApplication.BankApplication.service.ExportStatementsExcel;
import com.BankingApplication.BankApplication.service.SaveAccountStatementsToPDF;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AccountController {

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountService accountService;

	// save
	@PostMapping("/saveaccount/branchId/{branchId}")
	public ResponseEntity<ResponseStructure<Account>> saveAccount(@PathVariable int branchId,
			@RequestBody Account account) throws IOException {
		return accountService.saveAccount(branchId, account);
	}

	// upload profile
	@PatchMapping("/uploadProfile/accountNumber/{accountNumber}")
	public ResponseEntity<String> uploadProfile(@PathVariable long accountNumber, @RequestParam MultipartFile file)
			throws IOException {
		Account account = accountDao.getAccountByAccountNumber(accountNumber);
		account.setUserProfile(file.getBytes());
		accountRepository.save(account);
		return new ResponseEntity<>("profile is uploaded", HttpStatus.OK);
	}

	// fetch profile
	@GetMapping("/getprofile")
	public ResponseEntity<byte[]> getProfilePhoto(@RequestParam long accountNumber) {
		byte[] image = accountDao.getAccountByAccountNumber(accountNumber).getUserProfile();
		if (image != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.set(HttpHeaders.CONTENT_TYPE, "image/jpeg");
			return new ResponseEntity<>(image, headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

//	public ResponseEntity<byte[]> getProfilePhoto1(int accountNumber) {
//        byte[] image = accountDao.getAccountByAccountNumber(accountNumber).getUserProfile();
//        if (image != null) {
//            HttpHeaders headers = new HttpHeaders();
//            headers.set(HttpHeaders.CONTENT_TYPE, "image/jpeg");
//            return new ResponseEntity<>(image, headers, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//	@GetMapping("/getaccount")
//	public List<ResponseEntity<?>> getAccount(@RequestParam int accountNumber) {
//		ResponseEntity<byte[]> userProfile= getProfilePhoto1(accountNumber);
//		ResponseEntity<ResponseStructure<Account>> account= accountService.getAccountByAccountNumber(accountNumber);
//		List<ResponseEntity<?>> entities=new ArrayList<>();
//		entities.add(userProfile);
//		entities.add(account);
//		return entities;
//	}

	// update Name
	@PatchMapping("/updateName/accountNumber/{accountNumber}")
	public ResponseEntity<ResponseStructure<String>> updateName(@PathVariable long accountNumber,
			@RequestParam String name) {
		return accountService.updateName(accountNumber, name);
	}

	// update Phone
	@PatchMapping("/updatePhone/accountNumber/{accountNumber}")
	public ResponseEntity<ResponseStructure<String>> updatePhone(@PathVariable long accountNumber,
			@RequestParam long phone) {
		return accountService.updatePhone(accountNumber, phone);
	}

	// get account by id
	@GetMapping("/getaccount")
	public ResponseEntity<ResponseStructure<Account>> getAccount(@RequestParam long accountNumber) {

		return accountService.getAccountByAccountNumber(accountNumber);
	}

	// get all the accounts
	@GetMapping("/getallaccountsbybranch")
	public ResponseEntity<ResponseStructure<List<Account>>> getAllAccount(@RequestParam int branchId) {
		return accountService.getAllAccountsByBranch(branchId);
	}

	// get branch by account number
	@GetMapping("/getbranchbyaccount")
	public ResponseEntity<ResponseStructure<Branch>> getBranchByAccount(@RequestParam long accountNumber) {
		return accountService.getBranchByAccount(accountNumber);
	}

	// get number of accounts in a branch
	@GetMapping("/getcountbranchaccounts")
	public ResponseEntity<ResponseStructure<Integer>> getCountBranchAccounts(@RequestParam int branchId) {
		return accountService.getCountBranchAccounts(branchId);
	}

	// delete account
	@DeleteMapping("/deleteaccount")
	public ResponseEntity<ResponseStructure<String>> deleteAccount(@RequestParam long accountNumber) {
		return accountService.deleteAccount(accountNumber);
	}

	// update balance
	@PatchMapping("/updateBalance/accountNumber/{accountNumber}")
	public String updateBalance(@PathVariable long accountNumber, @RequestParam double balance) {
		return accountService.updateBalance(accountNumber, balance);
	}

	// transfer amount
	@PutMapping("/transferamount/fromAccountNumber/{fromAccountNumber}/toAccountNumber/{toAccountNumber}")
	public ResponseEntity<ResponseStructure<Statement>> tranferAmount(@PathVariable long fromAccountNumber,
			@PathVariable long toAccountNumber, @RequestParam double amount) {
		return accountService.transferAmount(fromAccountNumber, toAccountNumber, amount);
	}

	// get statements
	@GetMapping("/getstatements")
	public ResponseEntity<ResponseStructure<List<Statement>>> getAllStatements(@RequestParam long accountNumber) {
		return accountService.getAllStatements(accountNumber);
	}

	@Autowired
	private ExportStatementsExcel excel;

	@GetMapping("/sheet/accountNumber/{accountNumber}")
	public void generateExcelStatementSheet(HttpServletResponse response, @PathVariable long accountNumber)
			throws IOException {

		response.setContentType("application/octet-stream");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=accountTransaction.xls";

		response.setHeader(headerKey, headerValue);

		excel.exportDatExcel(accountNumber, response);
	}

	@Autowired
	private SaveAccountStatementsToPDF accountStatementsToPDF;

	@GetMapping("/statementsPdf")
	public void SaveAccountStatementsToPDF(HttpServletResponse response, @RequestParam long accountNumber)
			throws IOException {
		response.setContentType("application/pdf");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=accountTransaction.pdf";

		response.setHeader(headerKey, headerValue);

		accountStatementsToPDF.saveStatementsToPDF(response, accountNumber);
	}
}
