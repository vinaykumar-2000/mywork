package com.capstone.mobilestore.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_tbl")
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

	@Column(name = "customer_id")
    private long customerId; 
    
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL) 
    private List<OrderItem> orderItems;
    
    @Column(name = "order_total")
	private double orderTotal;

    
    @Column(name = "order_date")
	private LocalDate orderDate;
    
    private String status;
    
    @Column(name = "payment_method")
    private String paymentMethod;
}