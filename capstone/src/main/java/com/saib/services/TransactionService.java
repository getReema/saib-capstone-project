package com.saib.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.saib.models.Transaction;

@Service
public class TransactionService {

	public List<Transaction> getAllTransactions()
	{
		List<Transaction> list=new ArrayList<>();

		Transaction transaction1 = new Transaction(1, 100, 1010, "reema", "No", null, 50.0, LocalDateTime.now(), LocalDateTime.now(), "full", "completed");

		list.add(transaction1);

		return list;

	}
	
	
}
