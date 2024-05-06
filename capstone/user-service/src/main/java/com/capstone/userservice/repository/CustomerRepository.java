package com.capstone.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.userservice.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
