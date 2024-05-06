package com.capstone.mobilestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.mobilestore.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
