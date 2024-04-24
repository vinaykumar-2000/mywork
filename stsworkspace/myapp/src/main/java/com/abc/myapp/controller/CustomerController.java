package com.abc.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.myapp.model.Customer;
import com.abc.myapp.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/add")
	public String saveCustomer(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
		return "Customer saved";
	}

	@GetMapping("/{id}") 
	public Customer getCustomerDetails(@PathVariable("id") int customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		return customer;
	}
	
	@GetMapping("/all")
	public List<Customer> fetchAllCustomers(){
		List<Customer> customers = customerService.getAllCustomers();
		return customers;
	}
}
