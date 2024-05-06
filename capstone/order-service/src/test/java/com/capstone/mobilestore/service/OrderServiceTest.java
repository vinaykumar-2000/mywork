package com.capstone.mobilestore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.mobilestore.entity.Order;
import com.capstone.mobilestore.entity.OrderItem;
import com.capstone.mobilestore.exception.ItemNotFoundException;
import com.capstone.mobilestore.model.Customer;
import com.capstone.mobilestore.model.Mobile;
import com.capstone.mobilestore.model.OrderResponse;
import com.capstone.mobilestore.payload.OrderItemPayload;
import com.capstone.mobilestore.repository.OrderRepository;

@SpringBootTest
public class OrderServiceTest {

	@InjectMocks
	private OrderServiceImpl orderService;

	@Mock
	private OrderRepository orderRepository;

	@Mock
	private MobileServiceConsumer mobileService;

	@Mock
	private CustomerServiceConsumer customerService;

	@Test
	void testSaveOrder() {
		Order order = new Order();
		when(orderRepository.save(order)).thenReturn(order);
		Order savedOrder = orderService.saveOrder(order);
		assertEquals(order, savedOrder);
	}

	@Test
	void testCreateOrder() {
		long customerId = 1L;
		List<OrderItemPayload> items = new ArrayList<>();
		items.add(new OrderItemPayload(1L, 2)); // Assuming constructor takes mobileId and quantity

		Customer customer = new Customer();
		Mobile mobile = new Mobile();
		mobile.setPrice(500.00);

		when(customerService.getCustomerDetails(customerId)).thenReturn(customer);
		when(mobileService.getMobileDetails(1L)).thenReturn(mobile);

		Order result = orderService.createOrder(customerId, items);

		assertNotNull(result);
		assertEquals(LocalDate.now(), result.getOrderDate());
		assertEquals(1000.00, result.getOrderTotal());
		verify(orderRepository).save(result);
	}

	@Test
	void testGetOrderDetails() {
		long orderId = 1L;
		Order order = mock(Order.class);
		when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

		OrderResponse response = orderService.getOrderDetails(orderId);
		assertNotNull(response);
		verify(orderRepository).findById(orderId);
	}

	@Test
	void testGetOrderDetails_NotFound() {
		long orderId = 1L;
		when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

		assertThrows(ItemNotFoundException.class, () -> {
			orderService.getOrderDetails(orderId);
		});
	}

	@Test
	void testGetAllOrders() {
		OrderItem item1 = new OrderItem();
		item1.setId(1L);
		item1.setMobileId(101L);
		item1.setItemTotal(500.0);
		item1.setQuantity(2);

		OrderItem item2 = new OrderItem();
		item2.setId(2L);
		item2.setMobileId(102L);
		item2.setItemTotal(750.0);
		item2.setQuantity(1);

		Mobile mobile1 = new Mobile();
		mobile1.setId(101L);
		mobile1.setModel("Galaxy S10");
		mobile1.setPrice(500.0);

		Mobile mobile2 = new Mobile();
		mobile2.setId(102L);
		mobile2.setModel("iPhone 11");
		mobile2.setPrice(750.0);

		Order order1 = new Order();
		order1.setId(1L);
		order1.setOrderItems(Arrays.asList(item1));
		order1.setOrderTotal(1000.0);
		order1.setOrderDate(LocalDate.now());
		order1.setStatus("completed");
		order1.setPaymentMethod("credit card");
		order1.setCustomerId(1L);

		Order order2 = new Order();
		order2.setId(2L);
		order2.setOrderItems(Arrays.asList(item2));
		order2.setOrderTotal(750.0); // 1 * 750
		order2.setOrderDate(LocalDate.now());
		order2.setStatus("pending");
		order2.setPaymentMethod("debit card");
		order2.setCustomerId(2L);

		when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));
		when(mobileService.getMobileDetails(101L)).thenReturn(mobile1);
		when(mobileService.getMobileDetails(102L)).thenReturn(mobile2);

		List<OrderResponse> responses = orderService.getAllOrders();

		assertNotNull(responses);
		assertEquals(2, responses.size());

		verify(orderRepository).findAll();
		verify(mobileService, times(1)).getMobileDetails(101L);
		verify(mobileService, times(1)).getMobileDetails(102L);

		responses.forEach(response -> {
			System.out.println("Order ID: " + response.getId());
			response.getOrderItems().forEach(item -> {
				System.out.println("    Mobile ID: " + item.getMobile().getId());
				System.out.println("    Mobile Name: " + item.getMobile().getModel());
				System.out.println("    Item Total: " + item.getItemTotal());
				System.out.println("    Quantity: " + item.getQuantity());
			});
		});
	}

	@Test
	void testDeleteOrder() {
		long orderId = 1L;
		doNothing().when(orderRepository).deleteById(orderId);

		orderService.deleteOrder(orderId);
		verify(orderRepository).deleteById(orderId);
	}
}
