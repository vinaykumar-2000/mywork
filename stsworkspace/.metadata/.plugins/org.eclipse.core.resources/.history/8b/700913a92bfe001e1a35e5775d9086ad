package com.abc.abcmart.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.abcmart.entity.Customer;
import com.abc.abcmart.entity.Order;
import com.abc.abcmart.entity.OrderItem;
import com.abc.abcmart.entity.Product;
import com.abc.abcmart.exceptions.ResourceNotFoundException;
import com.abc.abcmart.payload.OrderItemPayload;
import com.abc.abcmart.repository.OrderRepository;

@Service
public class OrderServiceImpl implements Orderservice{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CustomerService customerService;

	@Override
	public Order saveOrder(Order order) {
		orderRepository.save(order);
		return order;
	}

	@Override
	public Order getOrderDetails(int orderid) {
		Optional<Order> optionalOrder = orderRepository.findById(orderid);
		if(optionalOrder.isEmpty()) {
			throw new ResourceNotFoundException("Order Not Found with this id :"+orderid);
		}
		
		Order order = optionalOrder.get();
		return order;
	}

	@Override
	public List<Order> getAllOrdersByCustomer(int customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		List<Order> customerOrders = customer.getOrders();
		return customerOrders;
	}

	@Override
	public Order createOrder(int customerId, List<OrderItemPayload> selectedProducts) {
		Customer customer = customerService.getCustomerById(customerId);
		
		Order order = new Order();
		order.setOrderDate (LocalDate.now());
		order.setOrderStatus ("pending");
		order.setCustomer (customer);
		
		List<OrderItem> orderItems = new ArrayList<>();
		
		double orderTotal = 0;
		
		for (OrderItemPayload po: selectedProducts) {
		    int productId = po.getProductId();
		    int qty = po.getQantity();
		    Product product = productService.getProductById(productId);
		    System.out.println("Itemtotal: "+product.getProductPrice()*qty);
		    
		    OrderItem orderItem = new OrderItem();
		    orderItem.setProduct (product);
		    orderItem.setItemTotal (product.getProductPrice()*qty);
		    orderItem.setQuantity(qty);
		    orderItem.setOrder(order);
		    orderItems.add(orderItem);
		    
		    orderTotal = orderTotal+(product.getProductPrice()*qty);
//		    orderItem.setOrder(order);
		}
		order.setOrderTotal (orderTotal);
		order.setOrderItems (orderItems);
		return order;
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> orderList = orderRepository.findAll();
		return orderList;
	}

	@Override
	public Order updateOrderStatus(int orderId, String status) {
		Order order = getOrderDetails(orderId);
		order.setOrderStatus(status);
		return saveOrder(order);
	}

	@Override
	public void cancelOrder(int orderId) {
		Order order = getOrderDetails(orderId);
		order.setOrderStatus("Canceled");
		saveOrder(order);
	}

}
