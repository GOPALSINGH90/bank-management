package com.cybrilla.service;

import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybrilla.Exceptions.NotFoundException;
import com.cybrilla.model.Customer;
import com.cybrilla.model.PayeeDetails;
import com.cybrilla.model.Transactions;
import com.cybrilla.repository.CustomerRepository;
import com.cybrilla.request.AccountRequest;
import com.cybrilla.request.CustomerRequest;
import com.cybrilla.response.AccountResponse;
import com.cybrilla.response.CustomerResponse;
import com.cybrilla.util.Uitility;

@Service("customerService")
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Transactional
	public CustomerResponse addCustomer(Customer customer) {
		CustomerResponse customerResponse = new CustomerResponse();

		String accNumber = getRandomNumberString();
		Customer isCustomerExist = customerRepository.getCustomer(accNumber);
		if (isCustomerExist != null) {
			addCustomer(customer);
		}
		customer.setAccountNumber(accNumber);
		Long generatedId = customerRepository.addCustomer(customer);
		if (generatedId != null) {
			customerResponse.setAccountNumber(accNumber);
			addTransactions(customer.getBalance(), accNumber, 1);
		}
		return customerResponse;
	}

	@Transactional
	public String updateCustomer(CustomerRequest customerRequest) {
		Customer customer = customerRepository.getCustomer(String.valueOf(customerRequest.getAccoutNumber()));
		if (customer != null) {
			if (customerRequest.getCreditORdebit() == 1 && customer.getStatus() == 1) {
				customer.setBalance(customer.getBalance() + customerRequest.getAmount());
			} else if (customerRequest.getCreditORdebit() == -1 && customer.getStatus() == 1) {
				if (customer.getBalance() >= customerRequest.getAmount()) {
					customer.setBalance(customer.getBalance() - customerRequest.getAmount());
				}
			}

			if (customerRequest.getPhoneNumber() != null
					&& !(customerRequest.getPhoneNumber().equals(customer.getPhoneNumber()))) {
				customer.setPhoneNumber(customerRequest.getPhoneNumber());
			}

			if (customerRequest.getStatus() != customer.getStatus()) {
				customer.setStatus(customerRequest.getStatus());
			}

			customerRepository.updateCustomer(customer);
			addTransactions(customerRequest.getAmount(), String.valueOf(customerRequest.getAccoutNumber()),
					customerRequest.getCreditORdebit());
		} else {
			throw new NotFoundException("Accout Not Found");
		}
		return "success";
	}

	private void addTransactions(Double balance, String accNumber, int creditdebit) {
		Transactions transactions = new Transactions(accNumber);
		if (creditdebit == 1) {
			transactions.setCredit(balance);
		} else if (creditdebit == -1) {
			transactions.setDebit(balance);
		}
		transactions.setCreatedTime(Uitility.getCurrentDateAndTimeInDate());

		customerRepository.addTransations(transactions);
	}

	public static String getRandomNumberString() {

		Random rnd = new Random();
		int number = rnd.nextInt(99999999);

		return String.format("%08d", number);
	}

	public AccountResponse getAccountSummary(String accountNumber) {
		AccountResponse accountResponse = new AccountResponse();
		try {
			Customer customer = customerRepository.getCustomer(accountNumber);
			accountResponse.setAccountNumber(accountNumber);
			accountResponse.setAmount(customer.getBalance());
			accountResponse.setAccountHolderName(customer.getCustomerName());

		} catch (Exception e) {
			System.out.println("exceptions" + e.toString());
		}

		return accountResponse;
	}

	public List<Transactions> getStatements(String accountNumber) {
		List<Transactions> transactions = customerRepository.getTransations(accountNumber);
		return transactions;
	}

	public void addPayee(PayeeDetails payeeDetails) {
		try {

			customerRepository.addPayee(payeeDetails);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public List<PayeeDetails> getPayees(AccountRequest accountRequest) {
		return customerRepository.getPayees(accountRequest);
	}

	public void fundTransfer(AccountRequest accountRequest) {
		Customer customer = customerRepository.getCustomer(accountRequest.getAccountNumber());
		if (customer != null && accountRequest.getAmount() >= 1) {
			if (accountRequest.getAmount() <= customer.getBalance()) {
				customer.setBalance(customer.getBalance() - accountRequest.getAmount());
			}
		}
		// call the api to deposit amount
		customerRepository.updateCustomer(customer);
	}

	public void receivefund(AccountRequest accountRequest) {
		Customer customer = customerRepository.getCustomer(accountRequest.getPayAccountNumer());
		if (customer != null && accountRequest.getAmount() >= 1) {
			if (accountRequest.getAmount() <= customer.getBalance()) {
				customer.setBalance(customer.getBalance() + accountRequest.getAmount());
			}
		}
		customerRepository.updateCustomer(customer);
	}
}