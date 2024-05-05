package com.capstone.mobilestore.service;

import java.util.List;

import com.capstone.mobilestore.entity.Order;
import com.capstone.mobilestore.model.OrderResponse;
import com.capstone.mobilestore.payload.OrderItemPayload;

public interface OrderService {

	Order saveOrder(Order order);
	Order createOrder(long customerId, List<OrderItemPayload> selectedMobiles);
    OrderResponse getOrderDetails(long orderId);
    List<OrderResponse> getAllOrders();
    Order updateOrder(long orderId, List<OrderItemPayload> updatedItems);
    void deleteOrder(long orderId);
}
