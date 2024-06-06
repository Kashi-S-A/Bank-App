package com.BankingApplication.BankApplication.entity;

import java.util.Date;

import com.BankingApplication.BankApplication.util.IdGeneratorService;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class DebitCard {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "card")
	@SequenceGenerator(name = "card",sequenceName = "card",allocationSize = 1)
	private long cardNumber;
	
	private int cvv;
	
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date expiryDate;
	
	private String name;
	
	private long phone;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToOne
	private Account account;
}
