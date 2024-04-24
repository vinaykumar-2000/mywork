package com.abc.abcmart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.abcmart.entity.Order;
import com.abc.abcmart.exceptions.ResourceNotFoundException;
import com.abc.abcmart.repository.OrderRepository;

@SpringBootTest
public class OrderServiceTest {

	@InjectMocks
	private OrderServiceImpl orderService;
	
	@Mock
	private OrderRepository orderRepository;
	
	@Test
    public void testSaveOrder() {
		
		Order order = new Order();
		order.setOrderId(11);
		order.setOrderDate (LocalDate.of(2000, 10, 10));
		order.setOrderStatus ("pending");	
		
		when(orderRepository.save(order)).thenReturn(order);
		
		Order savedOrder = orderService.saveOrder(order);
		 
		assertNotNull(savedOrder);
		assertEquals("pending",savedOrder.getOrderStatus());
		
		verify(orderRepository).save(order);
	}
	
	@Test
	public void testGetOrderDetails() {
		Order order = new Order();
		order.setOrderId(11);
		order.setOrderDate (LocalDate.of(2000, 10, 10));
		order.setOrderStatus ("pending");	
		
		when(orderRepository.findById(11)).thenReturn(Optional.of(order));
		
		Order actualObj = orderService.getOrderDetails(11);
		
		assertEquals(11, actualObj.getOrderId());
	}
	
	@Test
	public void testGetOrderDetailsException() {
		when(orderRepository.findById(10)).thenThrow(new ResourceNotFoundException("Order Not Found with this id : 10"));
		
		assertThrows(ResourceNotFoundException.class, ()->orderService.getOrderDetails(10));
	}
	
	@Test
    public void testUpdateOrderStatus() {
		Order order = new Order();
		order.setOrderId(11);
		order.setOrderDate (LocalDate.of(2000, 10, 10));
		order.setOrderStatus ("pending");	
		
		when(orderRepository.findById(11)).thenReturn(Optional.of(order));
		when(orderRepository.save(order)).thenReturn(order);
		
		order.setOrderStatus("Completed");
		Order updatedOrder = orderService.updateOrderStatus(11, "Completed");
		  
		 assertEquals("Completed", updatedOrder.getOrderStatus());
		 verify(orderRepository).save(order);
	}
	
	@Test
    public void testCancelOrder() {
		Order order = new Order();
		order.setOrderId(11);
		order.setOrderDate (LocalDate.of(2000, 10, 10));
		order.setOrderStatus ("pending");	
		
		 when(orderRepository.findById(11)).thenReturn(Optional.of(order));
	     when(orderRepository.save(order)).thenReturn(order);
	     
	     orderService.cancelOrder(11);

	     assertEquals("Canceled", order.getOrderStatus());
	     verify(orderRepository).save(order);
	}
}
