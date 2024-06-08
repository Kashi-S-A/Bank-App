package com.BankingApplication.BankApplication.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Setter
@Getter
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bankId;
	
	@Column(unique = true)
	private String bankName;
	
	@OneToOne(cascade = CascadeType.ALL)
	//@JsonIgnore
	private Address bankAddress;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Branch> branchs;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Employee employee;
}
