package com.abc.product.service;

import java.util.List;

import com.abc.product.entity.Product;



public interface ProductService {

	Product saveProduct(Product product);
	
	Product getProductById(int productId);
	
	List<Product> getAllProducts();
	
	Product updateProduct(Product product);
	
	void deleteProduct(int productId);
	
	List<Product> getProductsByCategory(String categoryName);
	
	List<Product> getProductsWithInPriceRanage(double minPrice,double maxPrice);
}
