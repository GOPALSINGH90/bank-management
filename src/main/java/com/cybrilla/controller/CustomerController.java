package com.cybrilla.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybrilla.model.Customer;
import com.cybrilla.model.PayeeDetails;
import com.cybrilla.model.Transactions;
import com.cybrilla.request.AccountRequest;
import com.cybrilla.response.AccountResponse;
import com.cybrilla.response.CustomerResponse;
import com.cybrilla.service.CustomerService;

@RestController
@RequestMapping("/api/customer/v1")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/get-accountsummary")
	public ResponseEntity<AccountResponse> getaccountsummry(@RequestBody AccountRequest AccountRequest) {
		AccountResponse accountResponse = customerService.getAccountSummary(AccountRequest.getAccountNumber());

		return new ResponseEntity<AccountResponse>(accountResponse, HttpStatus.CREATED);
	}

	@PostMapping("/get-statement")
	public ResponseEntity<List<Transactions>> getStatements(@RequestBody AccountRequest AccountRequest) {
		List<Transactions> transactions = customerService.getStatements(AccountRequest.getAccountNumber());
		return new ResponseEntity<List<Transactions>>(transactions, HttpStatus.OK);
	}

	@PostMapping("/add-payee")
	public ResponseEntity<String> addPayee(@RequestBody PayeeDetails payeeDetails) {
		customerService.addPayee(payeeDetails);
		return new ResponseEntity<String>("success", HttpStatus.CREATED);
	}

	@PostMapping("/get-payees")
	public ResponseEntity<List<PayeeDetails>> getPayees(@RequestBody AccountRequest AccountRequest) {
		List<PayeeDetails> payeeDetails = customerService.getPayees(AccountRequest);
		return new ResponseEntity<List<PayeeDetails>>(payeeDetails, HttpStatus.OK);
	}

	@PostMapping("/fund-transfer")
	public ResponseEntity<String> fundtransfer(@RequestBody AccountRequest AccountRequest) {
		customerService.fundTransfer(AccountRequest);

		return new ResponseEntity<String>("success", HttpStatus.OK);

	}

	@PostMapping("/fund-receive")
	public ResponseEntity<String> receivefund(@RequestBody AccountRequest AccountRequest) {
		customerService.fundTransfer(AccountRequest);
		return new ResponseEntity<String>("success", HttpStatus.OK);

	}
}
