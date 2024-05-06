package com.capstone.mobilestore.payload;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartPayload {

	private long customerId;
	private List<CartItemPayload> orderItems = new ArrayList<>();
}
