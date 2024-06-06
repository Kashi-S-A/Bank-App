package com.BankingApplication.BankApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BankingApplication.BankApplication.entity.DebitCard;

public interface DebitCardRepository extends JpaRepository<DebitCard, Integer>{

}
