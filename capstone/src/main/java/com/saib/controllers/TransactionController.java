package com.saib.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saib.exceptions.ApiSuccessPayload;
import com.saib.models.Transaction;
import com.saib.services.TransactionService;

@RestController
public class TransactionController {
	/*
	 *  GET - /transactions - Get me all details 
	 *  GET - /transactions/id - Get me details for a single account 
	 *  GET - transactions/type
	 *  
	 *  POST - /transactions - new 
	 *  PUT - /transactions/id - Updating an existing trans 
	 *  DELETE -/transactions/id - for deleting an trans from db
	 *  
	 *  
	 */
	
	@Autowired
	TransactionService transactionService;
	
	
	@GetMapping("/transactions")
	public ResponseEntity<ApiSuccessPayload> getAllTransactions() {
		List<Transaction> list = transactionService.getAllTransactions();

		ApiSuccessPayload payload = ApiSuccessPayload.build(list, "Accounts Fetched", HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response = new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);

		return response;

	}
	
	
	
	
	
}
