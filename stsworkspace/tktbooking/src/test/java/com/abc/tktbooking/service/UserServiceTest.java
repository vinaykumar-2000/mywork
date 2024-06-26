package com.abc.tktbooking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.tktbooking.entity.User;
import com.abc.tktbooking.exceptions.ResourceNotFoundException;
import com.abc.tktbooking.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {


	@InjectMocks
	private UserServiceImpl userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Test
	public void testSaveUser() {
		User user = new User();
		user.setUserId(101);
		user.setFirstName("testUser");
		user.setLastName("Last");
		user.setEmail("test@example.com");
		user.setAge(20);
		user.setMobile("9177633645");
		
		when(userRepository.save(user)).thenReturn(user);
		
		User savedUser = userService.saveUser(user);
		
		assertNotNull(savedUser);
		assertEquals("testUser", savedUser.getFirstName());
	}
	
	@Test
	public void testGetUserDetails() {
		User user = new User();
		user.setUserId(101);
		user.setFirstName("testUser");
		user.setLastName("Last");
		user.setEmail("test@example.com");
		user.setAge(20);
		user.setMobile("9177633645");
		
		
		when(userRepository.findById(101)).thenReturn(Optional.of(user));
		
		User actualUser = userService.getUserById(101);
		assertEquals("testUser", actualUser.getFirstName());
	}
	
	@Test
	public void testGetUserDetailsException() {
		when(userRepository.findById(100)).thenThrow(new ResourceNotFoundException("User not found with id : 100"));
		assertThrows(ResourceNotFoundException.class, ()->userService.getUserById(100));
	}
	
	@Test
	public void testGetAllUsers() {
		User user = new User();
		user.setUserId(101);
		user.setFirstName("testUser");
		user.setLastName("Last");
		user.setEmail("test@example.com");
		user.setAge(20);
		user.setMobile("9177633645");
		
		User user2 = new User();
		user2.setUserId(102);
		user2.setFirstName("user2");
		user2.setLastName("Last2");
		user2.setEmail("user2@example.com");
		user2.setAge(20);
		user2.setMobile("9177633645");
		
		
		List<User> userList = new ArrayList<>();
		userList.add(user);
		userList.add(user2);
		
		when(userRepository.findAll()).thenReturn(userList);
		
		List<User> actualUserList = userService.getAllUsers();
		
		assertEquals(2, actualUserList.size());
	}
	
	@Test
	public void testUpdateUser() {
		User user = new User();
		user.setUserId(101);
		user.setFirstName("testUser");
		user.setLastName("Last");
		user.setEmail("test@example.com");
		user.setAge(20);
		user.setMobile("9177633645");
		
		
		when(userRepository.findById(101)).thenReturn(Optional.of(user));
		when(userRepository.save(user)).thenReturn(user);
		
		User updatedUser = userService.updateUser(user);
		
		assertNotNull(updatedUser);
		assertEquals("testUser", updatedUser.getFirstName());
	}
	
	@Test
	public void testUpdateUserException() {
		User user = new User();
		user.setUserId(101);
		user.setFirstName("testUser");
		user.setLastName("Last");
		user.setEmail("test@example.com");
		user.setAge(20);
		user.setMobile("9177633645");
		
		when(userRepository.findById(100)).thenThrow(new ResourceNotFoundException("User not found with id : 100"));
		
		assertThrows(ResourceNotFoundException.class, ()->userService.updateUser(user));
		
		verify(userRepository,times(0)).save(user);
	}
	
	@Test
	public void testDeleteUser() {
		User user = new User();
		user.setUserId(101);
		user.setFirstName("testUser");
		user.setLastName("Last");
		user.setEmail("test@example.com");
		user.setAge(20);
		user.setMobile("9177633645");
		
		
		when(userRepository.findById(101)).thenReturn(Optional.of(user));
		
		doNothing().when(userRepository).delete(user);
		
		userService.deleteUser(101);
		
		verify(userRepository,times(1)).findById(101);
		verify(userRepository,times(1)).delete(user);
	}
	
	@Test
	public void testDeleteUserWithException() {
		User user = new User();
		user.setUserId(101);
		user.setFirstName("testUser");
		user.setLastName("Last");
		user.setEmail("test@example.com");
		user.setAge(20);
		user.setMobile("9177633645");
		
		when(userRepository.findById(100)).thenThrow(new ResourceNotFoundException("User not found with id : 100"));
		assertThrows(ResourceNotFoundException.class, ()->userService.deleteUser(100));
		
		verify(userRepository,times(0)).delete(user);
	}
	
}
