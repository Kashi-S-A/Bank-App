package com.BankingApplication.BankApplication.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Setter
@Getter
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "accountNumber")
	@SequenceGenerator(name = "accountNumber",sequenceName = "account_req",allocationSize = 1)
	private long accountNumber;
	
	private String accountHolderName;
		
	private long phoneNumber;
	
	private long adharNumber;
	
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Address address;
	
	@ManyToOne
	@JsonIgnore
	private Branch branch;
	
	@OneToOne
	@JsonIgnore
	private DebitCard debitCard;
	
	private double balance;
	
	@OneToMany
	@JsonIgnore
	private List<Statement> statements;
}
