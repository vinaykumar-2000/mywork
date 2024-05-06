package com.capstone.mobilestore.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CartItemResponse {

	private Long id;
    private Mobile mobile;
    private int quantity;
    private double itemTotal;
}
