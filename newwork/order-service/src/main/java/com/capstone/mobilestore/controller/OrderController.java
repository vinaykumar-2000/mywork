package com.capstone.mobilestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.mobilestore.entity.Order;
import com.capstone.mobilestore.model.OrderResponse;
import com.capstone.mobilestore.payload.OrderItemPayload;
import com.capstone.mobilestore.payload.OrderPayload;
import com.capstone.mobilestore.service.OrderServiceImpl;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderServiceImpl orderService;
	
	@PostMapping("/save/{customerId}")
	public ResponseEntity<Order> placeOrder(@PathVariable("customerId") long customerId,@RequestBody List<OrderItemPayload> orderPayload){
		
		Order order = orderService.createOrder(customerId, orderPayload);
        Order newOrder = orderService.saveOrder(order);

        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<OrderResponse> fetchOrderDetails(@PathVariable("id") long orderid){
		OrderResponse order = orderService.getOrderDetails(orderid);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	
	@GetMapping("/all")
	public List<OrderResponse> fetchAllOrders(){
		List<OrderResponse> orders = orderService.getAllOrders();
		return orders;
	}
	
	 @DeleteMapping("/{orderId}")
	    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") long orderId) {
	        orderService.deleteOrder(orderId);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
}
