package com.abc.myapp.dao;

import java.util.List;
import java.util.Optional;

import com.abc.myapp.model.Customer;

public interface CustomerDao {

	Optional<Customer> find(int customerId);

	List<Customer> findAll();

	void save(Customer customer);
}
