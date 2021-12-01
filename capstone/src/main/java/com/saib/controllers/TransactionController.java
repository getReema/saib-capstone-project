package com.saib.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saib.exceptions.ApiSuccessPayload;
import com.saib.models.Account;
import com.saib.models.Transaction;
import com.saib.services.TransactionService;
import com.saib.util.Results;

@RestController
public class TransactionController {
	/*
	 *  GET - /transactions - Get me all details  - DONE
	 *  GET - /transactions/id - Get me details for a single transaction  - DONE
	 *  GET - transactions/type
	 *  
	 *  POST - /transactions - new transaction - DONE
	 *  PUT - /transactions/id - Updating an existing trans -
	 *  DELETE -/transactions/id - for deleting an trans from db
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
	
	
	@GetMapping("/transactions/{transactionID}")
	public ResponseEntity<ApiSuccessPayload> getAccountbyAccountNumber(@PathVariable long transactionID)
	{
		Transaction transaction=transactionService.getTransactionTransactionID(transactionID);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(transaction, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	
	
	@PostMapping("/transactions")
	public ResponseEntity<ApiSuccessPayload> addTransaction(@RequestBody Transaction transaction)
	{
		ResponseEntity<ApiSuccessPayload> response=null;
		System.out.println(transaction); // check if it's recived well?
		String result=transactionService.addTransaction(transaction);
		if(result.equalsIgnoreCase(Results.SUCCESS))
		{
			ApiSuccessPayload payload=ApiSuccessPayload.build(result, "Account created successfully", HttpStatus.CREATED);
			response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.CREATED);
		}
		
		return response;
	
	}
	
	@PutMapping("/transactions/{transactionID}")
	public ResponseEntity<ApiSuccessPayload> updateTransaction(@RequestBody Transaction transaction, @PathVariable long transactionID)
	{
		String result=transactionService.updateTransaction(transaction, transactionID);
		ApiSuccessPayload payload=ApiSuccessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);
		return response;
	}
	
	
	@DeleteMapping("/transactions/{transactionID}")
	public ResponseEntity<ApiSuccessPayload> deleteTransaction(@PathVariable long transactionID)
	{
		String result=transactionService.deleteTransaction(transactionID);
		ApiSuccessPayload payload=ApiSuccessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);
		return response;
	}
	
	//Pagination
			@GetMapping("/transactions/all")
			public ResponseEntity <ApiSuccessPayload> getAllTransaction (@RequestParam int pageNumber, @RequestParam int pageSize){
				
				List<Transaction> list=transactionService.getAllTransaction(pageNumber,pageSize);
				HttpStatus status=HttpStatus.OK; // create status here to use directly in following lines instead of HttpSatus.OK
				ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Accounts Found",status); 
				ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, status);
				return response;
				
			}
			
			//Sorting
			@GetMapping("/transactions/all/sorted")
			public ResponseEntity <ApiSuccessPayload> getAllTransaction (@RequestParam int pageNumber, @RequestParam int pageSize, @RequestParam String sortBy){
				
				List<Transaction> list=transactionService.getAllTransaction(pageNumber,pageSize, sortBy);
				HttpStatus status=HttpStatus.OK; // create status here to use directly in following lines instead of HttpSatus.OK
				ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Accounts Found",status); 
				ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, status);
				return response;
				
			}
			
			//getTransactionByType
			@GetMapping("/transactions/type/{transactionType}")
			public ResponseEntity<ApiSuccessPayload> getTransactionByType(@PathVariable String transactionType)
			{
				List<Transaction> list=transactionService.getTransactionByType(transactionType);
				
				ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Success",HttpStatus.OK);
				ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
				return response;
			}
			
			
			//findTransactionByDate
			@GetMapping("/transactions/date/{date}")
			public ResponseEntity<ApiSuccessPayload> findTransactionByDate( @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date)
			{
				List<Transaction> list=transactionService.findTransactionByDate(date);
				ApiSuccessPayload payload= ApiSuccessPayload.build(list, "Success",HttpStatus.OK);
				ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
				return response;
			}
			
			//findTransactionByDateAndTransactionType
			@GetMapping("/transactions/{transactionType}/{date}")
			public ResponseEntity<ApiSuccessPayload> findTransactionByDateAndTransactionType( @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, String transactionType )
			{
				List<Transaction> list=transactionService.findTransactionByDateAndTransactionType(date, transactionType);
				ApiSuccessPayload payload= ApiSuccessPayload.build(list, "Success",HttpStatus.OK);
				ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
				return response;
			}
}
