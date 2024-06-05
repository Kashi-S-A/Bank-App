package com.BankingApplication.BankApplication.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Setter
@Getter
public class Branch {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int branchId;
	
	private String branchName;
	
	private String branchIFSCcode;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address branchAddress;
	
	@Column
	private String branchEmail;
	
	@Column
	private long branchPhoneNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Employee employee;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Bank bank;
	
	@OneToMany
	private List<Account> accounts;
	
}
