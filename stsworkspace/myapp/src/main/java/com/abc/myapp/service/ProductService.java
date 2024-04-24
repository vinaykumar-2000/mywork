package com.abc.myapp.service;

import java.util.List;

import com.abc.myapp.model.Product;

public interface ProductService {

	void addProduct(Product product);
	
	Product getProductById(int productId);
	
	List<Product> getAllProducts();
}
