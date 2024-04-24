package com.abc.myapp.dao;

import java.util.List;
import java.util.Optional;

import com.abc.myapp.model.Product;

public interface ProductDao {

	Optional<Product> find(int productId);
	
	List<Product> findAll();
	
	void save(Product product);
}
