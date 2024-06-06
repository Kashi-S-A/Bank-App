package com.BankingApplication.BankApplication.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Statement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int statementId;

	private double amount;

	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;

	@CreationTimestamp
	private Date date;

	@ManyToOne
	@JsonIgnore
	private Account account;

	@ManyToOne
	@JsonIgnoreProperties(value = { "phoneNumber", "adharNumber", "balance", "email", })
	private Account toAccount;
}
