package com.abc.abcmart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.abcmart.entity.Product;
import com.abc.abcmart.exceptions.ResourceNotFoundException;
import com.abc.abcmart.repository.ProductRepository;

@SpringBootTest
public class ProductServiceTest {
	
	@InjectMocks
	private ProductServiceImpl productService;
	
	@Mock
	private ProductRepository productRepository;
	
	@Test
	public void testSaveProduct() {
		Product product = new Product();
		product.setProductId(111);
		product.setProductName("ABCProduct");
		product.setProductPrice(100);
		product.setMfd(LocalDate.of(2000, 10, 10));
		product.setCategory("mobile");
		
		when(productRepository.save(product)).thenReturn(product);
		
		Product savedProduct = productService.saveProduct(product);
		
		assertNotNull(savedProduct);
		assertEquals("ABCProduct", savedProduct.getProductName());
	}
	
	@Test
	public void testGetProductDetails() {
		Product product = new Product();
		product.setProductId(111);
		product.setProductName("ABCProduct");
		product.setProductPrice(100);
		product.setMfd(LocalDate.of(2000, 10, 10));
		product.setCategory("mobile");
		
		when(productRepository.findById(111)).thenReturn(Optional.of(product));
		
		Product actualObj = productService.getProductById(111);
		assertEquals("ABCProduct", actualObj.getProductName());
	}
	
	@Test
	public void testGetProductDetailsException() {
		when(productRepository.findById(100)).thenThrow(new ResourceNotFoundException("Product not Existing with id : 100"));
		
		assertThrows(ResourceNotFoundException.class, ()->productService.getProductById(100));
	}
	
	@Test
	public void testGetAllProducts() {
		
		Product product = new Product();
		product.setProductId(111);
		product.setProductName("ABProduct");
		product.setProductPrice(1000);
		product.setMfd(LocalDate.of(2020, 10, 10));
		product.setCategory("mobile");
		
		Product product1 = new Product();
		product1.setProductId(101);
		product1.setProductName("ABCProduct");
		product1.setProductPrice(1001);
		product1.setMfd(LocalDate.of(2001, 10, 10));
		product1.setCategory("mobile");
		
		Product product2 = new Product();
		product2.setProductId(110);
		product2.setProductName("ACProduct");
		product2.setProductPrice(100);
		product2.setMfd(LocalDate.of(2019, 10, 10));
		product2.setCategory("mobile");
		
		List<Product> products = new ArrayList<>();
		products.add(product2);
		products.add(product1);
		products.add(product);
		
		when(productRepository.findAll()).thenReturn(products);
		
		List<Product> productList = productService.getAllProducts();
		
		assertEquals(3, productList.size());
	}
	
	@Test
	public void testUpdateProduct() {
		Product product = new Product();
		product.setProductId(111);
		product.setProductName("ABProduct");
		product.setProductPrice(1000);
		product.setMfd(LocalDate.of(2020, 10, 10));
		product.setCategory("mobile");
		
		when(productRepository.findById(111)).thenReturn(Optional.of(product));
		when(productRepository.save(product)).thenReturn(product);
		
		Product updatedProduct = productService.updateProduct(product);
		
		assertNotNull(updatedProduct);
		assertEquals("ABProduct", updatedProduct.getProductName());
	}
	
	@Test
	public void testUpdateProductException() {
		Product product = new Product();
		product.setProductId(111);
		product.setProductName("ABProduct");
		product.setProductPrice(1000);
		product.setMfd(LocalDate.of(2020, 10, 10));
		product.setCategory("mobile");
		
		when(productRepository.findById(100)).thenThrow(new ResourceNotFoundException("Product not Existing with id : 100"));
		
		assertThrows(ResourceNotFoundException.class, ()->productService.updateProduct(product));
		
		verify(productRepository,times(0)).save(product);
	}
	
	@Test
	void testDeleteProduct() {
		
		Product product = new Product();
		product.setProductId(111);
		product.setProductName("ABCProduct");
		product.setProductPrice(100);
		product.setMfd(LocalDate.of(2000, 10, 10));
		product.setCategory("mobile");
		
		when(productRepository.findById(111)).thenReturn(Optional.of(product));
		
		doNothing().when(productRepository).delete(product);
		
		productService.deleteProduct(111);
		
		verify(productRepository,times(1)).findById(111);
		verify(productRepository, times(1)).delete(product);
	}
	
	@Test
	void testDeleteProductWithException() {
		Product product = new Product();
		product.setProductId(111);
		product.setProductName("ABCProduct");
		product.setProductPrice(100);
		product.setMfd(LocalDate.of(2000, 10, 10));
		product.setCategory("mobile");
		
		when(productRepository.findById(100)).thenThrow(new ResourceNotFoundException("Product not Existing with id : 100"));
		
		assertThrows(ResourceNotFoundException.class, ()->productService.deleteProduct(100));
		
		verify(productRepository,times(0)).delete(product);
	}
}
