package com.capstone.mobilestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.mobilestore.entity.Cart;
import com.capstone.mobilestore.model.CartResponse;
import com.capstone.mobilestore.payload.CartItemPayload;
import com.capstone.mobilestore.service.CartServiceImpl;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartServiceImpl cartService;

	@PostMapping("/save/{customerId}")
	public ResponseEntity<Cart> placeOrder(@PathVariable("customerId") long customerId,@RequestBody List<CartItemPayload> cartPayload){
		
		Cart cart = cartService.createCart(customerId, cartPayload);
        Cart newCart = cartService.saveCart(cart);

        return new ResponseEntity<>(newCart, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<CartResponse> fetchCartDetails(@PathVariable("id") long cartId){
		CartResponse cart = cartService.getCartDetails(cartId);
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}
	
	
	@GetMapping("/all")
	public List<CartResponse> fetchAllCarts(){
		List<CartResponse> cart = cartService.getAllCarts();
		return cart;
	}
	
	 @DeleteMapping("/{cartId}")
	    public ResponseEntity<Void> deleteCart(@PathVariable("cartId") long cartId) {
	        cartService.deleteCart(cartId);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
	
}