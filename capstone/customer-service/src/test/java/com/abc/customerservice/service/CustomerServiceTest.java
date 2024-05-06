package com.abc.customerservice.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.customerservice.entity.Customer;
import com.abc.customerservice.exceptions.ResourceNotFoundException;
import com.abc.customerservice.repository.CustomerRepository;

@SpringBootTest
public class CustomerServiceTest {

	@InjectMocks
    private CustomerServiceImpl customerService;
	
	@Mock
    private CustomerRepository customerRepository;

    private Customer customer;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer();
        customer.setId(1);
        customer.setUsername("John Doe");
        customer.setEmail("john@example.com");
    }
    
    @Test
    public void testSaveCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        Customer savedCustomer = customerService.saveCustomer(customer);
        assertNotNull(savedCustomer);
        assertEquals("John Doe", savedCustomer.getUsername());
    }
    
    @Test
    public void testGetCustomerByIdFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Customer foundCustomer = customerService.getCustomerById(1L);
        assertNotNull(foundCustomer);
        assertEquals("John Doe", foundCustomer.getUsername());
    }

    @Test
    public void testGetCustomerByIdNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            customerService.getCustomerById(1L);
        });
    }
    
    @Test
    public void testGetAllCustomers() {
        Customer anotherCustomer = new Customer();
        anotherCustomer.setId(2);
        anotherCustomer.setUsername("Jane Doe");
        List<Customer> customerList = Arrays.asList(customer, anotherCustomer);
        when(customerRepository.findAll()).thenReturn(customerList);

        List<Customer> customers = customerService.getAllCustomers();
        assertNotNull(customers);
        assertEquals(2, customers.size());
        assertEquals("Jane Doe", customers.get(1).getUsername());
    }
    
    @Test
    public void testUpdateCustomerSuccess() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        customer.setEmail("newemail@example.com");
        Customer updatedCustomer = customerService.updateCustomer(customer);
        assertNotNull(updatedCustomer);
        assertEquals("newemail@example.com", updatedCustomer.getEmail());
    }

    @Test
    public void testUpdateCustomerNotFound() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            customerService.updateCustomer(customer);
        });
    }

    @Test
    public void testDeleteCustomerSuccess() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        doNothing().when(customerRepository).delete(customer);

        assertDoesNotThrow(() -> customerService.deleteCustomer(1L));
        verify(customerRepository, times(1)).delete(customer);
    }

    @Test
    public void testDeleteCustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            customerService.deleteCustomer(1L);
        });
    }

}
