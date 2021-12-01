package com.saib.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	
		
		
		// recives  the new transaction and the transaction id to be updated
		public String updateTransaction(Transaction transaction, long transactionID)
		{
			String result="";

			// edit the object of transaction
			transaction.setTransactionID(transactionID);
			// then save the transaction
			Transaction updatedTransaction=transactionRepository.save(transaction);

			if(updatedTransaction!=null) // check the returned from save()
			{
				result=Results.SUCCESS;
			}
			else
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record was not updated"); 
			}
			return result;

		}
	
		//deleteTransaction
		// recives the  Transaction Id to be deleted
		public String deleteTransaction(long transactionID)
		{
			String result="";
			try {
				
			transactionRepository.deleteById(transactionID);
				result=Results.SUCCESS;
				return result;
			}
			catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
			}


		}
		
		// Pagination 
		public List<Transaction> getAllTransaction(Integer pageNo, Integer pageSize) {

			Pageable paging = PageRequest.of(pageNo, pageSize);

			Page<Transaction> pageResult = transactionRepository.findAll(paging);

			int totalElements = pageResult.getNumberOfElements();
			int total = pageResult.getTotalPages();

			System.out.println("total=" + total + "  totalElements=" + totalElements);

			if (pageResult.hasContent()) {

				return pageResult.getContent();
			} else {
				return new ArrayList<Transaction>(); // return empty array
			}

}
	
		
		// Sorting 
		public List<Transaction> getAllTransaction(Integer pageNo, Integer pageSize,String sortBy) {

			Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

			Page<Transaction> pageResult = transactionRepository.findAll(paging);

			int totalElements = pageResult.getNumberOfElements();
			int total = pageResult.getTotalPages();

			System.out.println("total=" + total + "  totalElements=" + totalElements);

			if (pageResult.hasContent()) {

				return pageResult.getContent();
			} else {
				return new ArrayList<Transaction>(); // return empty array
			}

}
		
		public List<Transaction> getTransactionByType(String transactionType){
			
			List<Transaction> list = transactionRepository.findTransactionByTransactionType(transactionType);
			return list;
		}
		
			
		public List<Transaction> findTransactionByDate(LocalDate date){
			List<Transaction> list = transactionRepository.findTransactionByDate(date);
			return list;
		}
		
		//findTransactionByDate
		
		//findTransactionByDateAndTransactionType
		public List<Transaction> findTransactionByDateAndTransactionType(LocalDate date, String transactionType){
			List<Transaction> list = transactionRepository.findTransactionByDateAndTransactionType(date,transactionType);
			return list;
		}
}

