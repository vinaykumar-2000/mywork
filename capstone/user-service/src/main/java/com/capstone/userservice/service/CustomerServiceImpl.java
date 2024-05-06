package com.capstone.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.userservice.entity.Customer;
import com.capstone.userservice.exception.ResourceNotFoundException;
import com.capstone.userservice.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerById(Long id) {
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		if(optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Customer not Existing with this id : "+id);
		}
		Customer customer = optionalCustomer.get();
			return customer;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer updateCustomer(Long id, Customer customerDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(Long id) {
		if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found with ID: " + id);
        }
        customerRepository.deleteById(id);
		
	}

}
