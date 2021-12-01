package com.saib.repository;


import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.saib.models.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository <Transaction,Long> {
	List <Transaction> findTransactionByTransactionType(String transactionType);
	List <Transaction> findTransactionByDate(LocalDate date);
	List <Transaction> findTransactionByDateAndTransactionType(LocalDate date, String transactionType);
}
