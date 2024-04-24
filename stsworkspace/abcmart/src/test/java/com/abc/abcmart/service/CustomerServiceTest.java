package com.abc.abcmart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.abcmart.entity.Customer;
import com.abc.abcmart.exceptions.ResourceNotFoundException;
import com.abc.abcmart.repository.CustomerRepository;

@SpringBootTest
public class CustomerServiceTest {
	
	@InjectMocks
	private CustomerServiceImpl customerService;
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Test
	public void testSaveCustomer() {
		Customer customer = new Customer();
		customer.setCustomerId(101);
		customer.setFirstName("Ravi");
		customer.setLastName("Kumar");
		customer.setAge(20);
		customer.setEmail("ravi@gmail.com");
		customer.setMobile("8645874591");
		
		when(customerRepository.save(customer)).thenReturn(customer);
		
		Customer savedCustomer = customerService.saveCustomer(customer);
		
		assertNotNull(savedCustomer);
		assertEquals("Ravi", savedCustomer.getFirstName());
	}
	
	@Test
	public void testGetCustomerDetails() {
		Customer customer = new Customer();
		customer.setCustomerId(101);
		customer.setFirstName("Ravi");
		customer.setLastName("Kumar");
		customer.setAge(20);
		customer.setEmail("ravi@gmail.com");
		customer.setMobile("8645874591");
		
		when(customerRepository.findById(101)).thenReturn(Optional.of(customer));
		
		Customer actualObj = customerService.getCustomerById(101);
		assertEquals("Ravi", actualObj.getFirstName());
	}
	
	@Test
	public void testGetCustomerDetailsException() {
		when(customerRepository.findById(100)).thenThrow(new ResourceNotFoundException("Customner not Existing with id : 100"));
		assertThrows(ResourceNotFoundException.class, ()->customerService.getCustomerById(100));
		
	}
	
	@Test
	public void testGetAllCustomers() {
		Customer customer = new Customer();
		customer.setCustomerId(101);
		customer.setFirstName("Ravi");
		customer.setLastName("Kumar");
		customer.setAge(20);
		customer.setEmail("ravi@gmail.com");
		customer.setMobile("8645874591");
		
		Customer customer1 = new Customer();
		customer1.setCustomerId(102);
		customer1.setFirstName("Raj");
		customer1.setLastName("Kumar");
		customer1.setAge(20);
		customer1.setEmail("raj@gmail.com");
		customer1.setMobile("9145874591");
		
		List<Customer> customers = new ArrayList<>();
		customers.add(customer);
		customers.add(customer1);
		
		when(customerRepository.findAll()).thenReturn(customers);
		
		List<Customer> customerList = customerService.getAllCustomers();
		
		assertEquals(2, customerList.size());
	}
	
	@Test
	public void testUpdateCustomer() {
		Customer customer = new Customer();
		customer.setCustomerId(101);
		customer.setFirstName("Ravi");
		customer.setLastName("Kumar");
		customer.setAge(20);
		customer.setEmail("ravi@gmail.com");
		customer.setMobile("8645874591");
		
		when(customerRepository.findById(101)).thenReturn(Optional.of(customer));
		when(customerRepository.save(customer)).thenReturn(customer);
		
		Customer updatedCustomer = customerService.updateCustomer(customer);
		
		assertNotNull(updatedCustomer);
		assertEquals("Ravi", updatedCustomer.getFirstName());
	}
	
	@Test
	public void testUpdateCustomerException() {
		Customer customer = new Customer();
		customer.setCustomerId(101);
		customer.setFirstName("Ravi");
		customer.setLastName("Kumar");
		customer.setAge(20);
		customer.setEmail("ravi@gmail.com");
		customer.setMobile("8645874591");
		
		when(customerRepository.findById(100)).thenThrow(new ResourceNotFoundException("Customner not Existing with id : 100"));
		
		assertThrows(ResourceNotFoundException.class, ()->customerService.updateCustomer(customer));
		
		verify(customerRepository,times(0)).save(customer);
	}
	
	@Test
	void testDeleteCustomer() {
		Customer customer = new Customer();
		customer.setCustomerId(101);
		customer.setFirstName("Ravi");
		customer.setLastName("Kumar");
		customer.setAge(20);
		customer.setEmail("ravi@gmail.com");
		customer.setMobile("8645874591");
		
		when(customerRepository.findById(101)).thenReturn(Optional.of(customer));
		
		doNothing().when(customerRepository).delete(customer);
		
		customerService.deleteCustomer(101);
		
		verify(customerRepository,times(1)).findById(101);
		verify(customerRepository,times(1)).delete(customer);
		
	}
	
	@Test
	void testDeleteCustomerWithException() {
		Customer customer = new Customer();
		customer.setCustomerId(101);
		customer.setFirstName("Ravi");
		customer.setLastName("Kumar");
		customer.setAge(20);
		customer.setEmail("ravi@gmail.com");
		customer.setMobile("8645874591");
		
		when(customerRepository.findById(100)).thenThrow(new ResourceNotFoundException("Customner not Existing with id : 100"));
		assertThrows(ResourceNotFoundException.class, ()->customerService.deleteCustomer(100));
		
		verify(customerRepository,times(0)).delete(customer);
	}
}