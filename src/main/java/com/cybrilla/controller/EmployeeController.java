package com.cybrilla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybrilla.model.Customer;
import com.cybrilla.request.CustomerRequest;
import com.cybrilla.response.CustomerResponse;
import com.cybrilla.service.CustomerService;

@RestController
@RequestMapping("/api/employee/v1")
public class EmployeeController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/create-account")
	public ResponseEntity<CustomerResponse> addNewAccount(@RequestBody Customer customer) {
		CustomerResponse customerResponse = customerService.addCustomer(customer);
		return new ResponseEntity<CustomerResponse>(customerResponse, HttpStatus.CREATED);
	}

	@PutMapping("/credit-debit-update")
	public ResponseEntity<String> creditDebtiBalance(@RequestBody CustomerRequest customerRequest) {
		String status = customerService.updateCustomer(customerRequest);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}

}