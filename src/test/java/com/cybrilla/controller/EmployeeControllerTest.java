package com.cybrilla.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cybrilla.model.Customer;
import com.cybrilla.response.CustomerResponse;
import com.cybrilla.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeControllerTest {

	@Autowired
	private WebApplicationContext was;

	private MockMvc mockMvc;

	Customer customer;

	CustomerResponse customerResponse;

	@MockBean
	private CustomerService customerService;

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

	}

	@Test
	public void testAddNewAccount() throws Exception {
		Mockito.when(customerService.addCustomer(Mockito.any())).thenReturn(customerResponse);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/employee/v1/create-account").content(asJsonString(customerResponse))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
