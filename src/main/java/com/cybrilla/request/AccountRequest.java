package com.cybrilla.request;

public class AccountRequest {

	public String accountNumber;

	public String payAccountNumer;

	public Double amount;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPayAccountNumer() {
		return payAccountNumer;
	}

	public void setPayAccountNumer(String payAccountNumer) {
		this.payAccountNumer = payAccountNumer;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
