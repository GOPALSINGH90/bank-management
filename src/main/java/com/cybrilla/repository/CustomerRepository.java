package com.cybrilla.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cybrilla.model.Customer;
import com.cybrilla.model.PayeeDetails;
import com.cybrilla.model.Transactions;
import com.cybrilla.request.AccountRequest;

@Repository
public interface CustomerRepository {

	public Customer getCustomer(String accountNumber);

	public Long addCustomer(Customer customer);

	public void updateCustomer(Customer customer);

	public void addTransations(Transactions transactions);

	public List<Transactions> getTransations(String accountNumber);

	public void addPayee(PayeeDetails payeeDetails);
	
	public List<PayeeDetails> getPayees(AccountRequest accountRequest);

}
