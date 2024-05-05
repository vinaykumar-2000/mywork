package com.capstone.mobilestore.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemPayload {

	private long mobileId;
	private int qantity;
}
