package com.BankingApplication.BankApplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.BankingApplication.BankApplication.entity.DebitCard;

public interface DebitCardRepository extends JpaRepository<DebitCard, Integer>{

	@Query("SELECT d FROM DebitCard d WHERE cardNumber=:cardNumber")
	Optional<DebitCard> findById(long cardNumber);

//	@Query("UPDATE DebitCard d SET d.account=null WHERE d.cardNumber=:cardNumber")
//	public void setAccountNull(long cardNumber);
}
