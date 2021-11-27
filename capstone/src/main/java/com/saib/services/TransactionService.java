package com.saib.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.saib.models.Account;
import com.saib.models.Transaction;
import com.saib.repository.TransactionRepository;
import com.saib.util.Results;

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
	
	
	
	public Transaction getTransactionTransactionID(long transactionID)
	{
		Optional<Transaction> optional=transactionRepository.findById(transactionID); // if exisit it will return.
		
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account with Account Number:"+transactionID+"doesn't exist"); // throw error if it doesn't exist 
		}
		
	}
	
	
	//for Post /transaction
		public String addTransaction(Transaction transaction)
		{
			String result="";
			Transaction storedTransaction=transactionRepository.save(transaction); // using JPA method .save(), it will return the object just added
			if(storedTransaction!=null) { // if saved in DB and is ok? check the returned object
				result=Results.SUCCESS; // Results is list of codes object 
			}
			else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not created");
			}
			
			return result;
		}
	
	
	
	
}
