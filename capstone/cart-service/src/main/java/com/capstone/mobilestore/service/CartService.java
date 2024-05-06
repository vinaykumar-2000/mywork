package com.capstone.mobilestore.service;

import java.util.List;

import com.capstone.mobilestore.entity.Cart;
import com.capstone.mobilestore.model.CartResponse;
import com.capstone.mobilestore.payload.CartItemPayload;

public interface CartService {
	
	Cart saveCart(Cart cart);
	Cart createCart(long customerId, List<CartItemPayload> selectedMobiles);
	CartResponse getCartDetails(long cartId);
	List<CartResponse> getAllCarts();
	Cart updateCart(long cartId, List<CartItemPayload> updatedItems);
	void deleteCart(long cartId);

}
