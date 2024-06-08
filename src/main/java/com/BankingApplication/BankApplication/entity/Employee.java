package com.BankingApplication.BankApplication.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int employeeId;

	private String name;

	@Column()
	private long phoneNumber;

	@Column()
	private String email;

	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	private Address employeeAddress;
	
	private String gender;

	private Date dateOfBirth;

	@CreationTimestamp
	private Date dateOfJoining;

	private String token;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToOne
	@JsonIgnore
	private Bank bank;
	
	@OneToOne
	private Branch branch;

}
