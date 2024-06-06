package com.BankingApplication.BankApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BankingApplication.BankApplication.entity.DebitCard;
import com.BankingApplication.BankApplication.entity.Status;
import com.BankingApplication.BankApplication.response.ResponseStructure;
import com.BankingApplication.BankApplication.service.DebitCardService;

@RestController
public class DebitCardController {

	@Autowired
	private DebitCardService cardService;

	// save
	@PostMapping("/savecard/accountNumber/{accountNumber}")
	public ResponseEntity<ResponseStructure<DebitCard>> saveDebitCard(@PathVariable int accountNumber,@RequestBody DebitCard debitCard) {
		return cardService.saveDebitCard(accountNumber,debitCard);
	}

	// get card
	@GetMapping("/getcard")
	public ResponseEntity<ResponseStructure<DebitCard>> getDebitCardByCardNumber(@RequestParam int debitCardNumber) {
		return cardService.getCardByCardNumber(debitCardNumber);
	}

	// update name
	@PatchMapping("/updatecardname/cardNumber/{cardNumber}")
	public ResponseEntity<ResponseStructure<DebitCard>> updateCardName(@PathVariable int cardNumber,
			@RequestParam String name) {
		return cardService.updateName(cardNumber, name);
	}

	// update phone
	@PatchMapping("/updatecardPhone/cardNumber/{cardNumber}")
	public ResponseEntity<ResponseStructure<DebitCard>> updateCardPhone(@PathVariable int cardNumber,
			@RequestParam long phone) {
		return cardService.updatePhone(cardNumber, phone);
	}

	// update status
	@PatchMapping("/updatecardStatus/cardNumber/{cardNumber}")
	public ResponseEntity<ResponseStructure<DebitCard>> updateCardStatus(@PathVariable int cardNumber,
			@RequestParam Status status) {
		return cardService.updateStatus(cardNumber, status);
	}
	
	//delete card
	@DeleteMapping("/deletecard")
	public ResponseEntity<ResponseStructure<String>> deleteDebitCard(@RequestParam int cardNumber) {
		return cardService.deleteDebitCard(cardNumber);
	}
}
