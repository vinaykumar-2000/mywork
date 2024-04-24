package com.abc.myapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.myapp.dao.CustomerDao;
import com.abc.myapp.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public void addCustomer(Customer customer) {
		customerDao.save(customer);
		
	}

	@Override
	public Customer getCustomerById(int customerId) {
		Optional<Customer> optionalCustomer = customerDao.find(customerId);
		return optionalCustomer.get();
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = customerDao.findAll();
		return customers;
	}

}
