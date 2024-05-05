package com.capstone.mobilestore.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.mobilestore.entity.Cart;
import com.capstone.mobilestore.entity.CartItem;
import com.capstone.mobilestore.exception.ItemNotFoundException;
import com.capstone.mobilestore.model.CartItemResponse;
import com.capstone.mobilestore.model.CartResponse;
import com.capstone.mobilestore.model.Customer;
import com.capstone.mobilestore.model.Mobile;
import com.capstone.mobilestore.payload.CartItemPayload;
import com.capstone.mobilestore.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;
   
    @Autowired
    private CustomerServiceConsumer customerService;
    
    @Autowired
    private MobileServiceConsumer mobileService;

	@Override
	public Cart saveCart(Cart cart) {
		cartRepository.save(cart);
		return cart;
	}

	@Override
	public Cart createCart(long customerId, List<CartItemPayload> selectedMobiles) {
		Customer customer = customerService.getCustomerDetails(customerId);
		
		Cart cart  = new Cart();
		cart.setCustomerId(customerId);
		cart.setStatus("Pending");
		cart.setCartDate(LocalDate.now());
		
		List<CartItem> cartItems = new ArrayList<>();
		
		double cartTotal = 0;
		
		for(CartItemPayload po : selectedMobiles) {
			long mobileId = po.getMobileId();
			int qty = po.getQuantity();
			Mobile mobile = mobileService.getMobileDetails(mobileId);
			System.out.println("ItemTotal : "+ mobile.getPrice() * qty);
			
			CartItem cartItem = new CartItem();
			cartItem.setMobileId(mobileId);
			cartItem.setItemTotal(mobile.getPrice() * qty);
			cartItem.setQuantity(qty);
			cartItem.setCart(cart);
			cartItems.add(cartItem);
			
			cartTotal = cartTotal  + (mobile.getPrice() * qty);
			
		}
		
		cart.setCartTotal(cartTotal);
		cart.setCartItems(cartItems);
		saveCart(cart);
		return cart;
		
	}

	@Override
	public CartResponse getCartDetails(long cartId) {
		 Optional<Cart> optionalCart = cartRepository.findById(cartId);
	        if (optionalCart.isEmpty()) {
	            throw new ItemNotFoundException("Cart Not Found with this id: " + cartId);
	        }

	        Cart cart = optionalCart.get();

	        CartResponse cartResponse = new CartResponse();
	        cartResponse.setId(cart.getId());
	        cartResponse.setCartDate(cart.getCartDate());
	        cartResponse.setCartTotal(cart.getCartTotal());
	        cartResponse.setStatus(cart.getStatus());

	        Long customerId = cart.getCustomerId();
	        Customer customer = customerService.getCustomerDetails(customerId);
	        cartResponse.setCustomer(customer);

	        List<CartItemResponse> cartItems = new ArrayList<>();

	        List<CartItem> items = cart.getCartItems();

	        for (CartItem item : items) {
	            CartItemResponse cartItemResp = new CartItemResponse();
	            cartItemResp.setId(item.getMobileId());
	            cartItemResp.setItemTotal(item.getItemTotal());
	            cartItemResp.setQuantity(item.getQuantity());

	            cartItems.add(cartItemResp);
	        }

	        cartResponse.setCartItems(cartItems);

	        return cartResponse;
	}

	@Override
	public List<CartResponse> getAllCarts() {
		 List<Cart> cartList = cartRepository.findAll();
		    List<CartResponse> cartResponses = new ArrayList<>();

		    for (Cart cart : cartList) {
		        CartResponse cartResponse = new CartResponse();
		        cartResponse.setId(cart.getId());
		        cartResponse.setCartDate(cart.getCartDate());
		        cartResponse.setCartTotal(cart.getCartTotal());
		        cartResponse.setStatus(cart.getStatus());

		        Long customerId = cart.getCustomerId();
		        Customer customer = customerService.getCustomerDetails(customerId);
		        cartResponse.setCustomer(customer);

		        List<CartItemResponse> cartItems = new ArrayList<>();
		        List<CartItem> cartItemList = cart.getCartItems();

		        for (CartItem cartItem : cartItemList) {
		            CartItemResponse cartItemResponse = new CartItemResponse();
		            cartItemResponse.setId(cartItem.getId());
		            cartItemResponse.setItemTotal(cartItem.getItemTotal());
		            cartItemResponse.setQuantity(cartItem.getQuantity());
		            
		            long mobileId = cartItem.getMobileId();
		            Mobile mobile = mobileService.getMobileDetails(mobileId);
		            cartItemResponse.setMobile(mobile);
		            
		            cartItems.add(cartItemResponse);
		        }

		        cartResponse.setCartItems(cartItems);
		        cartResponses.add(cartResponse);
		    }

		    return cartResponses;
	}

	@Override
	public Cart updateCart(long cartId, List<CartItemPayload> updatedItems) {
		Optional<Cart> optionalCart = cartRepository.findById(cartId);
	    if (optionalCart.isEmpty()) {
	        throw new ItemNotFoundException("Cart not found with id: " + cartId);
	    }

	    Cart cart = optionalCart.get();

	    List<CartItem> existingCartItems = cart.getCartItems();
	    Map<Long, CartItem> existingItemsMap = existingCartItems.stream()
	        .collect(Collectors.toMap(CartItem::getMobileId, item -> item));

	    double cartTotal = 0;

	    for (CartItemPayload payload : updatedItems) {
	        long mobileId = payload.getMobileId();
	        int updatedQuantity = payload.getQuantity();
	        
	        Mobile mobile = mobileService.getMobileDetails(mobileId);

	        if (updatedQuantity > 0) {
	            if (existingItemsMap.containsKey(mobileId)) {
	                CartItem item = existingItemsMap.get(mobileId);
	                item.setQuantity(updatedQuantity);
	                item.setItemTotal(mobile.getPrice() * updatedQuantity);
	                cartTotal += item.getItemTotal();
	            } else {
	               
	                CartItem newItem = new CartItem();
	                newItem.setCart(cart);
	                newItem.setMobileId(mobileId);
	                newItem.setQuantity(updatedQuantity);
	                newItem.setItemTotal(mobile.getPrice() * updatedQuantity);
	                existingCartItems.add(newItem);
	                cartTotal += newItem.getItemTotal();
	            }
	        } else {
	            existingItemsMap.remove(mobileId);
	        }
	    }

	    existingCartItems.removeIf(item -> !existingItemsMap.containsKey(item.getMobileId()));

	    cart.setCartTotal(cartTotal);
	    cart.setCartItems(new ArrayList<>(existingItemsMap.values()));
	    return saveCart(cart);
	}

	@Override
	public void deleteCart(long cartId) {
		cartRepository.deleteById(cartId);
		
	}

	
}