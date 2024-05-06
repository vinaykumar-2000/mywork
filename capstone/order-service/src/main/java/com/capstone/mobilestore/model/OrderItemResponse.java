package com.capstone.mobilestore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {

	private long id;
	private int quantity;
	private Mobile mobile;
	private double itemTotal;
}
