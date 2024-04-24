package com.abc.abcmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abc.abcmart.entity.Order;
import com.abc.abcmart.payload.OrderItemPayload;
import com.abc.abcmart.payload.OrderPayload;
import com.abc.abcmart.service.Orderservice;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private Orderservice orderService;
	
	@PostMapping("/save")
	public ResponseEntity<Order> placeOrder(@RequestBody OrderPayload orderPayload){
		
		int customerId = orderPayload.getCustomerId();
		List<OrderItemPayload> productOrders = orderPayload.getOrderItems();
		
		Order order = orderService.createOrder(customerId, productOrders);
		Order newOrder = orderService.saveOrder(order);
		
		return new ResponseEntity<>(newOrder,HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Order> fetchOrderDetails(@PathVariable("id") int orderid){
		Order order = orderService.getOrderDetails(orderid);
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	@GetMapping("/customer")
	public List<Order> fetchAllOrdersByCustomer(@RequestParam("customerId") int customerId){
		List<Order> orders = orderService.getAllOrdersByCustomer(customerId);
		return orders;
	}
	
	@GetMapping("/all")
	public List<Order> fetchAllOrders(){
		List<Order> orders = orderService.getAllOrders();
		return orders;
	}
	
	@PostMapping("/{id}/status")
	public ResponseEntity<Order> updateOrderStatus(@PathVariable("id") int orderId, @RequestParam("status") String status){
		Order updatedOrder = orderService.updateOrderStatus(orderId, status);
		return new ResponseEntity<>(updatedOrder,HttpStatus.OK);
	}
	
	@PostMapping("/{id}/cancel")
	public ResponseEntity<String> cancelOrder(@PathVariable("id") int orderId){
		orderService.cancelOrder(orderId);
		return new ResponseEntity<>("Order canceled successfully",HttpStatus.OK);
	}
}