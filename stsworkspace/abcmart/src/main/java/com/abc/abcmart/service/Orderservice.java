package com.abc.abcmart.service;

import java.util.List;

import com.abc.abcmart.entity.Order;
import com.abc.abcmart.payload.OrderItemPayload;

public interface Orderservice {

	Order createOrder(int customerId, List<OrderItemPayload> selectedProducts);
	
	Order saveOrder(Order order);
	
	Order getOrderDetails(int orderid);
	
	List<Order> getAllOrders();
	
	List<Order> getAllOrdersByCustomer(int customerId);
	
	Order updateOrderStatus(int orderId, String status);
	
	void cancelOrder(int orderId);
}
