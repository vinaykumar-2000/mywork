package com.abc.myapp.service;

import java.util.List;

import com.abc.myapp.model.Customer;

public interface CustomerService {

	void addCustomer(Customer customer);

	Customer getCustomerById(int customerId);

	List<Customer> getAllCustomers();
}
