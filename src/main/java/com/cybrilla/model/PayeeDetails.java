package com.cybrilla.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payee")
public class PayeeDetails {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "payeeName")
	private String payeeName;

	@Column(name = "payeeAccNumber")
	private String payeeAccNumber;

	@Column(name = "accountNumber")
	private String accountNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getPayeeAccNumber() {
		return payeeAccNumber;
	}

	public void setPayeeAccNumber(String payeeAccNumber) {
		this.payeeAccNumber = payeeAccNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "PayeeDetails [id=" + id + ", payeeName=" + payeeName + ", payeeAccNumber=" + payeeAccNumber + "]";
	}

}
