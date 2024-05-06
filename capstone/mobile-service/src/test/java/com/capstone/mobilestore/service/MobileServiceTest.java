package com.capstone.mobilestore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.mobilestore.entity.Mobile;
import com.capstone.mobilestore.exception.ResourceNotFoundException;
import com.capstone.mobilestore.repository.MobileRepository;

@SpringBootTest
public class MobileServiceTest {

	@InjectMocks
	private MobileServiceImpl mobileService;

	@Mock
	private MobileRepository mobileRepository;

	@Test
	void testSaveMobile() {
		Mobile mobile = new Mobile();
		mobile.setId(1L);
		mobile.setBrand("Samsung");
		mobile.setModel("Galaxy S21");
		mobile.setPrice(999.99);

		when(mobileRepository.save(mobile)).thenReturn(mobile);

		Mobile savedMobile = mobileService.saveMobile(mobile);

		assertEquals(mobile, savedMobile);

		verify(mobileRepository).save(mobile);
	}

	@Test
	void testFindAllMobiles() {
		Mobile mobile1 = new Mobile();
		mobile1.setId(1L);
		mobile1.setModel("iPhone 12");
		Mobile mobile2 = new Mobile();
		mobile2.setId(2L);
		mobile2.setModel("Galaxy S21");

		when(mobileRepository.findAll()).thenReturn(Arrays.asList(mobile1, mobile2));

		List<Mobile> result = mobileService.findAllMobiles();

		assertNotNull(result);
		assertEquals(2, result.size());
		verify(mobileRepository).findAll();
	}

	@Test
	void testFindMobileById_Found() {
		Mobile mobile = new Mobile();
		mobile.setId(1L);
		mobile.setModel("iPhone 12");

		when(mobileRepository.findById(1L)).thenReturn(Optional.of(mobile));

		Mobile result = mobileService.findMobileById(1L);

		assertNotNull(result);
		assertEquals("iPhone 12", result.getModel());
		verify(mobileRepository).findById(1L);
	}

	@Test
	void testFindMobileById_NotFound() {
		when(mobileRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			mobileService.findMobileById(1L);
		});
	}

	@Test
	void testDeleteMobile_Success() {
		Mobile mobile = new Mobile();
		mobile.setId(1L);

		when(mobileRepository.findById(1L)).thenReturn(Optional.of(mobile));
		doNothing().when(mobileRepository).delete(mobile);

		mobileService.deleteMobile(1L);

		verify(mobileRepository).delete(mobile);
	}

	@Test
	void testDeleteMobile_NotFound() {
		when(mobileRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			mobileService.deleteMobile(1L);
		});
	}

	@Test
	void testSearchMobilesByBrand() {
		Mobile mobile1 = new Mobile();
		mobile1.setId(1L);
		mobile1.setBrand("Apple");
		Mobile mobile2 = new Mobile();
		mobile2.setId(2L);
		mobile2.setBrand("Apple");

		List<Mobile> mockMobiles = Arrays.asList(mobile1, mobile2);

		when(mobileRepository.searchByBrand("Apple")).thenReturn(mockMobiles);

		List<Mobile> result = mobileService.searchMobilesByBrand("Apple");

		assertEquals(2, result.size(), "The size of the result list should be 2");
		assertEquals("Apple", result.get(0).getBrand(), "The brand of the first mobile should be 'Apple'");

		verify(mobileRepository).searchByBrand("Apple");
	}

}
