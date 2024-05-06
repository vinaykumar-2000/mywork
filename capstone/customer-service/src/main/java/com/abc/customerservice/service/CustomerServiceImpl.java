package com.abc.customerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.customerservice.entity.Customer;
import com.abc.customerservice.exceptions.ResourceNotFoundException;
import com.abc.customerservice.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer saveCustomer(Customer customer) {
		customerRepository.save(customer);
		return customer;
	}

	@Override
	public Customer getCustomerById(long id) {
		Optional<Customer> optionalCustomer= customerRepository.findById(id);
		if(optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Customer not Existing with this Id : "+id);
		}
		Customer customer = optionalCustomer.get();
		return customer;
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		return customers;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Optional<Customer> optionalCustomer= customerRepository.findById(customer.getId());
		if(optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Customer not Existing with this Id : "+customer.getId());
		}
		customerRepository.save(customer);
		return customer;
	}

	@Override
	public void deleteCustomer(long id) {
		Optional<Customer> optionalCustomer= customerRepository.findById(id);
		if(optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Customer not Existing with this Id : "+id);
		}
		Customer customer = optionalCustomer.get();
		customerRepository.delete(customer);
	}

}
