package com.abc.myapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.myapp.dao.ProductDao;
import com.abc.myapp.model.Product;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;

	@Override
	public void addProduct(Product product) {
		productDao.save(product);
		
	}

	@Override
	public Product getProductById(int productId) {
		Optional<Product> optionalProduct = productDao.find(productId);
		return optionalProduct.get();
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> products = productDao.findAll();
		return products;
	}
	
	
	
}
