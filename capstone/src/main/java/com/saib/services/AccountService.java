package com.saib.services;

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
import com.saib.repository.AccountRepository;
import com.saib.util.Results;

import io.sentry.Sentry;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;

	public List<Account> getAllAccount() {
		List<Account> list = accountRepository.findAll();
		return list;

	}

	// for Get /accounts/{AccountNumber}
	public Account getAccountByAccountNumber(long accountNumber) {
		Optional<Account> optional = accountRepository.findById(accountNumber); // if exisit it will return.

		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Account with Account Number:" + accountNumber + "doesn't exist"); // throw error if it doesn't
																						// exist
		}

	}

	// for Post /accounts
	public String addAccount(Account account) {
		String result = "";
		Account storedAccount = accountRepository.save(account); // using JPA method .save(), it will return the object
																	// just added
		if (storedAccount != null) { // if saved in DB and is ok? check the returned object
			result = Results.SUCCESS; // Results is list of codes object
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not created");
		}

		return result;
	}

	// recives the account_object and the new accountNumber to be updated
	public String updateAccount(Account account, long accountNumber) {
		String result = "";

		// edit the object of account
		account.setAccountNumber(accountNumber);
		// then save the account
		Account updatedAccount = accountRepository.save(account);

		if (updatedAccount != null) // check the returned from save()
		{
			result = Results.SUCCESS;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record was not updated");
		}
		return result;

	}

	// recives the accountNumber to be deleted
	public String deleteAccount(long accountNumber) {
		String result = "";
		try {

			accountRepository.deleteById(accountNumber);
			result = Results.SUCCESS;
			return result;
		} catch (Exception e) {
			Sentry.captureException(e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	public List<Account> getAccountByType(String type) {
		List<Account> list = accountRepository.findByAccountType(type);
		return list;

	}

	public List<Account> getAccountByStatus(String status) {
		List<Account> list = accountRepository.findByStatus(status);
		return list;

	}

	public List<Account> getAccountsByGender(String gender) {

		List<Account> list = accountRepository.findAccountByGender(gender);
		return list;
	}

	// Pagination
	public List<Account> getAllAccount(Integer pageNo, Integer pageSize) {

		Pageable paging = PageRequest.of(pageNo, pageSize);

		Page<Account> pageResult = accountRepository.findAll(paging);

		int totalElements = pageResult.getNumberOfElements();
		int total = pageResult.getTotalPages();

		if (pageResult.hasContent()) {

			return pageResult.getContent();
		} else {
			return new ArrayList<Account>(); // return empty array
		}

	}

	// sorting
	public List<Account> getAllAccount(Integer pageNo, Integer pageSize, String sortBy) {

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<Account> pageResult = accountRepository.findAll(paging);

		int totalElements = pageResult.getNumberOfElements();
		int total = pageResult.getTotalPages();

		if (pageResult.hasContent()) {

			return pageResult.getContent();
		} else {
			return new ArrayList<Account>(); // return empty array
		}

	}

}
