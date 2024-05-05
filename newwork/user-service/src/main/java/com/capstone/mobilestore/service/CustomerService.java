package com.capstone.mobilestore.service;

import java.util.List;

import com.capstone.mobilestore.entity.Customer;

public interface CustomerService {

	Customer createCustomer(Customer customer);

	Customer getCustomerById(Long id);

	List<Customer> getAllCustomers();

	Customer updateCustomer(Long id, Customer customerDetails);

	void deleteCustomer(Long id);
}
