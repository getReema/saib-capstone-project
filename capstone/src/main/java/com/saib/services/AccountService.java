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
import com.saib.repository.AccountRepository;
import com.saib.util.Results;

@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	public List<Account> getAllAccount()
	{
		List<Account> list=accountRepository.findAll();
		
//		Account account1=new Account(1000100,"Zartab", "Male", "zartab@codewithz.com","7715012345", "Some Address", "Savings", 10000.00, 100000.00, LocalDateTime.now(), LocalDateTime.now(), "Active");
//		Account account2=new Account(1000100,"Sohail", "Male", "sohail@codewithz.com","7715012345", "Some Address", "Savings", 10000.00, 100000.00, LocalDateTime.now(), LocalDateTime.now(), "Active");
//		
//		list.add(account1);
//		list.add(account2);
		
		return list;
		
	}
	
	// for Get /accounts/{AccountNumber}
	public Account getAccountByAccountNumber(long accountNumber)
	{
		Optional<Account> optional=accountRepository.findById(accountNumber); // if exisit it will return.
		
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account with Account Number:"+accountNumber+"doesn't exist"); // throw error if it doesn't exist 
		}
		
	}
	
	
	//for Post /accounts
	public String addAccount(Account account)
	{
		String result="";
		Account storedAccount=accountRepository.save(account); // using JPA method .save(), it will return the object just added
		if(storedAccount!=null) { // if saved in DB and is ok? check the returned object
			result=Results.SUCCESS; // Results is list of codes object 
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not created");
		}
		
		return result;
	}
	
	
	// recives  the account_object and the new accountNumber to be updated
	public String updateAccount(Account account, long accountNumber)
	{
		String result="";

		// edit the object of account
		account.setAccountNumber(accountNumber);
		// then save the account
		Account updatedAccount=accountRepository.save(account);

		if(updatedAccount!=null) // check the returned from save()
		{
			result=Results.SUCCESS;
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record was not updated"); 
		}
		return result;

	}
	
	
	// recives the new accountNumber to be deleted
	public String deleteAccount(long accountNumber)
	{
		String result="";
		try {
			
		accountRepository.deleteById(accountNumber);
			result=Results.SUCCESS;
			return result;
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}


	}
	
	
	
	

}








