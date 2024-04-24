package com.abc.myapp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.abc.myapp.model.Customer;


@Repository
public class CustomerDaoImpl implements CustomerDao{

	private List<Customer> customers = new ArrayList<>();

	@Override
	public Optional<Customer> find(int customerId) {
		Optional<Customer> optionalCustomer =  customers.stream().filter(p->p.getCustomerId()==customerId).findAny();
		return optionalCustomer;
	}

	@Override
	public List<Customer> findAll() {
		return customers;
	}

	@Override
	public void save(Customer customer) {
		customers.add(customer);
	}
	
	
}
