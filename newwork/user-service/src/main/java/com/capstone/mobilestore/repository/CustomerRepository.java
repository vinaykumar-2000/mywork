package com.capstone.mobilestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.mobilestore.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
