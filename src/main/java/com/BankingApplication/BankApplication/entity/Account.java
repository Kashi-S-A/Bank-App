package com.BankingApplication.BankApplication.entity;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Setter
@Getter
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long accountNumber;
	
	private String accountHolderName;
		
	private long phoneNumber;
	
	private long adharNumber;
	
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@ManyToOne
	@JsonIgnore
	private Branch branch;
}
