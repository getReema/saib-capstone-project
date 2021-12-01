package com.saib.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.saib.services.AccountService;
import com.saib.util.Results;

@RestController
public class AccountController {
	
	/* Done:
	 *  GET - /accounts - Get me all details 
	 *  GET - /accounts/id - Get me details for a single account 
	 *  POST - /accounts - Creating a new account 
	 *  PUT - /accounts/id - Updating an existing account 
	 *  DELETE -/accounts/id - for deleting an account from db
	 *  
	 *  Not done:
	 *  GET /accounts/type
	 *  
	 */
	@Autowired
	AccountService accountService;
	
	
	@GetMapping("/accounts")
	public ResponseEntity<ApiSuccessPayload> getAllAccounts()
	{
		List<Account> list=accountService.getAllAccount();
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Accounts Fetched", HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		
		return response;
		
	}
	
	@GetMapping("/accounts/{accountNumber}")
	public ResponseEntity<ApiSuccessPayload> getAccountbyAccountNumber(@PathVariable long accountNumber)
	{
		Account account=accountService.getAccountByAccountNumber(accountNumber);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(account, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	@PostMapping("/accounts")
	public ResponseEntity<ApiSuccessPayload> addAccount(@RequestBody Account account)
	{
		ResponseEntity<ApiSuccessPayload> response=null;
		System.out.println(account);
		String result=accountService.addAccount(account);
		if(result.equalsIgnoreCase(Results.SUCCESS))
		{
			ApiSuccessPayload payload=ApiSuccessPayload.build(result, "Account created successfully", HttpStatus.CREATED);
			response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.CREATED);
		}
		
		return response;
	
	}

	
	@PutMapping("/accounts/{accountNumber}")
	public ResponseEntity<ApiSuccessPayload> updateAccount(@RequestBody Account account, @PathVariable long accountNumber)
	{
		String result=accountService.updateAccount(account, accountNumber);
		ApiSuccessPayload payload=ApiSuccessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/accounts/{accountNumber}")
	public ResponseEntity<ApiSuccessPayload> deleteAccount(@PathVariable long accountNumber)
	{
		String result=accountService.deleteAccount(accountNumber);
		ApiSuccessPayload payload=ApiSuccessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);
		return response;
	}
	
	
	
	//getAccountByType
	@GetMapping("/accounts/type/{type}")
	public ResponseEntity<ApiSuccessPayload> getAccountByType (@PathVariable String type) {
		List<Account> list=accountService.getAccountByType(type);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Accounts Fetched", HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		
		return response;
		
	}
	
	
	//Status
		@GetMapping("/accounts/status/{status}")
		public ResponseEntity<ApiSuccessPayload> getAccountBySype (@PathVariable String status) {
			List<Account> list=accountService.getAccountByStatus(status);
			
			ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Accounts Fetched", HttpStatus.OK);
			ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
			
			return response;
			
		}
	
	
		@GetMapping("/accounts/gender/{gender}")
		public ResponseEntity<ApiSuccessPayload> getAccountByGender(@PathVariable String gender)
		{
			List<Account> list=accountService.getAccountsByGender(gender);
			HttpStatus status=HttpStatus.OK; // create status here to use directly in following lines instead of HttpSatus.OK
			ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Accounts Found",status); 
			ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, status);
			return response;
			
		}
		
		//Pagination
		@GetMapping("/accounts/all")
		public ResponseEntity <ApiSuccessPayload> getAllAccounts (@RequestParam int pageNumber, @RequestParam int pageSize){
			
			List<Account> list=accountService.getAllAccount(pageNumber,pageSize);
			HttpStatus status=HttpStatus.OK; // create status here to use directly in following lines instead of HttpSatus.OK
			ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Accounts Found",status); 
			ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, status);
			return response;
			
		}
		
		//Sorting
		@GetMapping("/accounts/all/sorted")
		public ResponseEntity <ApiSuccessPayload> getAllAccounts (@RequestParam int pageNumber, @RequestParam int pageSize, @RequestParam String sortBy){
			
			List<Account> list=accountService.getAllAccount(pageNumber,pageSize, sortBy);
			HttpStatus status=HttpStatus.OK; // create status here to use directly in following lines instead of HttpSatus.OK
			ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Accounts Found",status); 
			ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, status);
			return response;
			
		}
		
		
}