package com.cybrilla.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cybrilla.model.Customer;
import com.cybrilla.repository.CustomerRepository;
import com.cybrilla.request.CustomerRequest;
import com.cybrilla.response.CustomerResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	private WebApplicationContext was;

	private MockMvc mockMvc;

	Customer customer;

	CustomerResponse customerResponse;
	
	CustomerRequest customerRequest;

	@MockBean
	CustomerRepository customerRepository;
	
	@MockBean
	CustomerService customerService;

	@Before
	public void init() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(was).build();
		customer = new Customer();
		customer.setAccountNumber("12345678");
		customer.setAddress("Bhopal");
		customer.setBalance(100.0);
		customer.setPhoneNumber(1234567890L);

		customerResponse = new CustomerResponse();
		customerResponse.setAccountNumber("12345678");
		
		customerRequest =  new CustomerRequest();
		customerRequest.setAccoutNumber(12345678L);
		customerRequest.setAmount(12.22);

	}

	@Test
	public void testUpdateCustomer() {
		Mockito.when(customerRepository.getCustomer(Mockito.any())).thenReturn(customer);
		String status =  customerService.updateCustomer(customerRequest);
		//assertThat(status).isEqualToIgnoringWhitespace("success");
	}

}
