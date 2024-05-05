package com.capstone.mobilestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.mobilestore.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
