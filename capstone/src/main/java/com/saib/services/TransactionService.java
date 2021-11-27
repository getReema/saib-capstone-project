package com.saib.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saib.models.Transaction;
import com.saib.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;
	
	
	public List<Transaction> getAllTransactions()
	{
		List<Transaction> list= transactionRepository.findAll();

		list.toString();
		return list;

	}
	
	
}
