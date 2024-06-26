package com.BankingApplication.BankApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int addressId;

	private String pincode;

	private String country;

	private String state;

	private String city;

}
