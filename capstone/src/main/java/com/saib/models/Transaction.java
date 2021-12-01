package com.saib.models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "transactions")
public class Transaction {

// write all attributes 
	@Id
	@Column(name = "transaction_id")
	private long transactionID;
	
	@Column(name = "from_account")
	private long fromAccount;
	
	@Column(name = "to_account")
	private long toAccounnt;
	
	@Column(name = "from_account_name")
	private String fromAccountName;
	
	@Column(name="to_account_name")
	private String toAccountName ;
	
	public String getToAccountName() {
		return toAccountName;
	}

	public void setToAccountName(String toAccountName) {
		this.toAccountName = toAccountName;
	}

	@Column(name = "same_bank_transaction")
	private String sameBankTransaction;
	
	@Column(name = "other_bank")
	private String otherBank;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "date")
	private LocalDate date;
	
	
	@Column(name = "time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="HH:mm:ss")
	private LocalTime time;
	
	@Column(name = "transaction_type")
	private String transactionType;
	
	@Column(name = "status")
	private String status;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(int transactionID, int fromAccount, int toAccounnt, String fromAccountName, String toAccountName
			,String sameBankTransaction, String otherBank, double amount, LocalDate date, LocalTime time,
			String transactionType, String status) {
		super();
		this.transactionID = transactionID;
		this.fromAccount = fromAccount;
		this.toAccounnt = toAccounnt;
		this.fromAccountName = fromAccountName;
		this.toAccountName = toAccountName;
		this.sameBankTransaction = sameBankTransaction;
		this.otherBank = otherBank;
		this.amount = amount;
		this.date = date;
		this.time = time;
		this.transactionType = transactionType;
		this.status = status;
	}

	public long getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
	}

	public long getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(long fromAccount) {
		this.fromAccount = fromAccount;
	}

	public long getToAccounnt() {
		return toAccounnt;
	}

	public void setToAccounnt(long toAccounnt) {
		this.toAccounnt = toAccounnt;
	}

	public String getFromAccountName() {
		return fromAccountName;
	}

	public void setFromAccountName(String fromAccountName) {
		this.fromAccountName = fromAccountName;
	}

	public String getSameBankTransaction() {
		return sameBankTransaction;
	}

	public void setSameBankTransaction(String sameBankTransaction) {
		this.sameBankTransaction = sameBankTransaction;
	}

	public String getOtherBank() {
		return otherBank;
	}

	public void setOtherBank(String otherBank) {
		this.otherBank = otherBank;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Transactions [transactionID=" + transactionID + ", fromAccount=" + fromAccount + ", toAccounnt="
				+ toAccounnt + ", fromAccountName=" + fromAccountName +"toAccountName"+ toAccountName+ ", sameBankTransaction=" + sameBankTransaction
				+ ", otherBank=" + otherBank + ", amount=" + amount + ", date=" + date + ", time=" + time
				+ ", transactionType=" + transactionType + ", status=" + status + "]";
	}


	
	
}
