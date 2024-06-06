package com.BankingApplication.BankApplication.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class DebitCard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "card")
	@SequenceGenerator(name = "card",initialValue =10000,sequenceName = "100000")
	@Column(length = 11,nullable = false)
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
