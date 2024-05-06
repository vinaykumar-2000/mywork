package com.capstone.mobilestore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.mobilestore.entity.Cart;
import com.capstone.mobilestore.entity.CartItem;
import com.capstone.mobilestore.model.CartResponse;
import com.capstone.mobilestore.model.Customer;
import com.capstone.mobilestore.model.Mobile;
import com.capstone.mobilestore.payload.CartItemPayload;
import com.capstone.mobilestore.repository.CartRepository;

@SpringBootTest
public class CartServiceTest {

	@InjectMocks
	private CartServiceImpl cartService;

	@Mock
	private CartRepository cartRepository;

	@Mock
	private CustomerServiceConsumer customerService;

	@Mock
	private MobileServiceConsumer mobileService;

	@Test
	void testSaveCart() {
		Cart Cart = new Cart();
		when(cartRepository.save(Cart)).thenReturn(Cart);
		Cart savedCart = cartService.saveCart(Cart);
		assertEquals(Cart, savedCart);
	}

	@Test
	void testCreateCart() {
		long customerId = 1L;
		List<CartItemPayload> items = new ArrayList<>();
		items.add(new CartItemPayload(1L, 2)); // Assuming constructor takes mobileId and quantity

		Customer customer = new Customer();
		Mobile mobile = new Mobile();
		mobile.setPrice(500.00);

		when(customerService.getCustomerDetails(customerId)).thenReturn(customer);
		when(mobileService.getMobileDetails(1L)).thenReturn(mobile);

		Cart result = cartService.createCart(customerId, items);

		assertNotNull(result);
		assertEquals(LocalDate.now(), result.getCartDate());
		assertEquals(1000.00, result.getCartTotal());
		verify(cartRepository).save(result);
	}

	@Test
	void testGetCartDetails() {
		long CartId = 1L;
		Cart Cart = mock(Cart.class);
		when(cartRepository.findById(CartId)).thenReturn(Optional.of(Cart));

		CartResponse response = cartService.getCartDetails(CartId);
		assertNotNull(response);
		verify(cartRepository).findById(CartId);
	}

	@Test
	void testGetAllCarts() {
		CartItem item1 = new CartItem();
		item1.setId(1L);
		item1.setMobileId(101L);
		item1.setItemTotal(500.0);
		item1.setQuantity(2);

		CartItem item2 = new CartItem();
		item2.setId(2L);
		item2.setMobileId(102L);
		item2.setItemTotal(750.0);
		item2.setQuantity(1);

		Mobile mobile1 = new Mobile();
		mobile1.setId(101L);
		mobile1.setModel("Galaxy S10");
		mobile1.setPrice(500.0);

		Mobile mobile2 = new Mobile();
		mobile2.setId(102L);
		mobile2.setModel("iPhone 11");
		mobile2.setPrice(750.0);

		Cart cart1 = new Cart();
		cart1.setId(1L);
		cart1.setCartItems(Arrays.asList(item1));
		cart1.setCartTotal(1000.0);
		cart1.setCartDate(LocalDate.now());
		cart1.setStatus("completed");
		cart1.setCustomerId(1L);

		Cart cart2 = new Cart();
		cart2.setId(2L);
		cart2.setCartItems(Arrays.asList(item2));
		cart2.setCartTotal(750.0); // 1 * 750
		cart2.setCartDate(LocalDate.now());
		cart2.setStatus("pending");
		cart2.setCustomerId(2L);

		when(cartRepository.findAll()).thenReturn(Arrays.asList(cart1, cart2));
		when(mobileService.getMobileDetails(101L)).thenReturn(mobile1);
		when(mobileService.getMobileDetails(102L)).thenReturn(mobile2);

		List<CartResponse> responses = cartService.getAllCarts();

		assertNotNull(responses);
		assertEquals(2, responses.size());

		verify(cartRepository).findAll();
		verify(mobileService, times(1)).getMobileDetails(101L);
		verify(mobileService, times(1)).getMobileDetails(102L);

		responses.forEach(response -> {
			System.out.println("Cart ID: " + response.getId());
			response.getCartItems().forEach(item -> {
				System.out.println("    Mobile ID: " + item.getMobile().getId());
				System.out.println("    Mobile Name: " + item.getMobile().getModel());
				System.out.println("    Item Total: " + item.getItemTotal());
				System.out.println("    Quantity: " + item.getQuantity());
			});
		});
	}

	@Test
	void testDeleteOrder() {
		long cartId = 1L;
		doNothing().when(cartRepository).deleteById(cartId);

		cartService.deleteCart(cartId);
		verify(cartRepository).deleteById(cartId);
	}
}