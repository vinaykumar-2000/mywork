package com.capstone.mobilestore.service;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.capstone.mobilestore.model.Customer;

@Component
public class CustomerServiceFallbackFactory implements FallbackFactory<CustomerServiceConsumer>{

	@Override
	public CustomerServiceConsumer create(Throwable cause) {
		// TODO Auto-generated method stub
		return new CustomerServiceConsumer() {
			
			@Override
			public Customer getCustomerDetails(long customerId) {
				// TODO Auto-generated method stub
				return new Customer();
			}
		};
	}

}
