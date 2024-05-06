package com.capstone.mobilestore.model;

import java.time.LocalDate;
import java.util.List;

import com.capstone.mobilestore.payload.CartItemPayload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CartResponse {

	private Long id;
	private double cartTotal;
	private LocalDate cartDate;
	private String status;
	private  Customer customer;
	private List<CartItemResponse> cartItems;
	
}
