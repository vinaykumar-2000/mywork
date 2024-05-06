package com.capstone.mobilestore.payload;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPayload {

	private long customerId;
	private List<OrderItemPayload> orderItems = new ArrayList<>();
}
