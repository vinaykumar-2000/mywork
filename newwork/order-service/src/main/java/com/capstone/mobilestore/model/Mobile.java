package com.capstone.mobilestore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mobile {

	private long id;
	private String model;
	private String brand;
	private double price;
}