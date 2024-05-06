package com.capstone.userservice.service;

import java.util.List;

import com.capstone.userservice.entity.Customer;

public interface CustomerService {

	Customer createCustomer(Customer customer);

	Customer getCustomerById(Long id);

	List<Customer> getAllCustomers();

	Customer updateCustomer(Long id, Customer customerDetails);

	void deleteCustomer(Long id);
}
