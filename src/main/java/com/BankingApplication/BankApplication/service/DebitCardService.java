package com.BankingApplication.BankApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.BankingApplication.BankApplication.dao.AccountDao;
import com.BankingApplication.BankApplication.dao.DebitCardDao;
import com.BankingApplication.BankApplication.entity.DebitCard;
import com.BankingApplication.BankApplication.entity.Status;
import com.BankingApplication.BankApplication.response.ResponseStructure;

@Service
public class DebitCardService {

	@Autowired
	private DebitCardDao cardDao;
	
	// save
	public ResponseEntity<ResponseStructure<DebitCard>> saveDebitCard(int accountNumber,DebitCard card) {
		DebitCard card2 = cardDao.saveDebitCard(accountNumber,card);
		ResponseStructure<DebitCard> responseStructure = new ResponseStructure<>();
		responseStructure.setData(card2);
		responseStructure.setMessage("success");
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<DebitCard>>(responseStructure, HttpStatus.CREATED);
	}

	// get by card number
	public ResponseEntity<ResponseStructure<DebitCard>> getCardByCardNumber(int cardNumber) {
		DebitCard card2 = cardDao.getDebitCardByNumber(cardNumber);
		ResponseStructure<DebitCard> responseStructure = new ResponseStructure<>();
		responseStructure.setData(card2);
		responseStructure.setMessage("success");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<DebitCard>>(responseStructure, HttpStatus.OK);

	}

	// update card name
	public ResponseEntity<ResponseStructure<DebitCard>> updateName(int cardNumber, String name) {
		DebitCard card2 = cardDao.updateName(cardNumber, name);
		ResponseStructure<DebitCard> responseStructure = new ResponseStructure<>();
		responseStructure.setData(card2);
		responseStructure.setMessage("success");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<DebitCard>>(responseStructure, HttpStatus.OK);
	}

	// update card phone
	public ResponseEntity<ResponseStructure<DebitCard>> updatePhone(int cardNumber, long phone) {
		DebitCard card2 = cardDao.updatePhone(cardNumber, phone);
		ResponseStructure<DebitCard> responseStructure = new ResponseStructure<>();
		responseStructure.setData(card2);
		responseStructure.setMessage("success");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<DebitCard>>(responseStructure, HttpStatus.OK);
	}

	// update card status
	public ResponseEntity<ResponseStructure<DebitCard>> updateStatus(int cardNumber, Status status) {
		DebitCard card2 = cardDao.updateStatus(cardNumber, status);
		ResponseStructure<DebitCard> responseStructure = new ResponseStructure<>();
		responseStructure.setData(card2);
		responseStructure.setMessage("success");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<DebitCard>>(responseStructure, HttpStatus.OK);
	}
	
	// delete card by number
	public ResponseEntity<ResponseStructure<String>> deleteDebitCard(int cardNumber) {
		String response = cardDao.deleteDebitCard(cardNumber);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setData(response);
		responseStructure.setMessage("success");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}
}
