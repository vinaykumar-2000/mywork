package com.abc.myapp.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.abc.myapp.model.Product;

@Repository
public class ProductDaoImpl  implements ProductDao{

	
	private List<Product> products = new ArrayList<>();
	
	
	@Override
	public Optional<Product> find(int productId) {

		Optional<Product> optionalProduct =  products.stream().filter(p->p.getProductId()==productId).findAny();
		
		return optionalProduct;
	}

	@Override
	public List<Product> findAll() {
		return products;
	}

	@Override
	public void save(Product product) {
		products.add(product);
	}

}
