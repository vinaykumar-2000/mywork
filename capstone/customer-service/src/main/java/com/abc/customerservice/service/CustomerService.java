package com.abc.customerservice.service;

import java.util.List;

import com.abc.customerservice.entity.Customer;



public interface CustomerService {
	
	Customer saveCustomer(Customer customer);
	
	Customer getCustomerById(long id);
	
	List<Customer> getAllCustomers();
	
	Customer updateCustomer(Customer customer);
	
	void deleteCustomer(long id);

}
