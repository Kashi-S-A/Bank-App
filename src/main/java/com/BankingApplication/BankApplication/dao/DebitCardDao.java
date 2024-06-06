package com.BankingApplication.BankApplication.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.BankingApplication.BankApplication.entity.Account;
import com.BankingApplication.BankApplication.entity.DebitCard;
import com.BankingApplication.BankApplication.entity.Status;
import com.BankingApplication.BankApplication.exception.AccountNotFoundException;
import com.BankingApplication.BankApplication.exception.DebitCardNotFoundException;
import com.BankingApplication.BankApplication.repository.DebitCardRepository;

@Repository
public class DebitCardDao {

	@Autowired
	private DebitCardRepository cardRepository;

	@Autowired
	private AccountDao accountDao;

	// save
	public DebitCard saveDebitCard(int accountNumber, DebitCard debitCard) {
		Account account = accountDao.getAccountByAccountNumber(accountNumber);
		account.setDebitCard(debitCard);
		debitCard.setAccount(account);
		return cardRepository.save(debitCard);
	}

	// save
	private DebitCard saveDebitCard(DebitCard debitCard) {
		return cardRepository.save(debitCard);
	}

	// update name
	public DebitCard getDebitCardByNumber(int cardNumber) {
		return cardRepository.findById(cardNumber)
				.orElseThrow(() -> new DebitCardNotFoundException("Card does not exist"));
	}

	// update name
	public DebitCard updateName(int cardNumber, String name) {
		DebitCard card = getDebitCardByNumber(cardNumber);
		card.setName(name);
		saveDebitCard(card);
		return card;
	}

	// update phone
	public DebitCard updatePhone(int cardNumber, long phone) {
		DebitCard card = getDebitCardByNumber(cardNumber);
		card.setPhone(phone);
		saveDebitCard(card);
		return card;
	}

	// update status
	public DebitCard updateStatus(int cardNumber, Status status) {
		DebitCard card = getDebitCardByNumber(cardNumber);
		card.setStatus(status);
		saveDebitCard(card);
		return card;
	}

	// delete card
	public String deleteDebitCard(int cardNumber) {
		DebitCard card = getDebitCardByNumber(cardNumber);
		Account account = card.getAccount();
		if (account != null) {
			account.setDebitCard(null);
		}
		card.setAccount(null);
		cardRepository.save(card);
		cardRepository.delete(card);
		return "deleted succussfully";
	}
}
