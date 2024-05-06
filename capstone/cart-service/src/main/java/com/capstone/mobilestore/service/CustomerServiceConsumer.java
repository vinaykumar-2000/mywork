package com.capstone.mobilestore.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capstone.mobilestore.model.Customer;

@FeignClient(name="CUSTOMER-SERVICE")
public interface CustomerServiceConsumer {

	@GetMapping("/customer/{id}")
	Customer getCustomerDetails(@PathVariable("id") long customerId);
	
}
