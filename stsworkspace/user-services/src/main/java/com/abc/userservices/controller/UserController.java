package com.abc.userservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.userservices.entity.User;
import com.abc.userservices.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public List<User> fetchAllUsers(){
		List<User> users = userService.getAllUsers();
		return users;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> fetchUserDetails(@PathVariable("id") int userId){
		User user = userService.getUserById(userId);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<User> addUser(@RequestBody User user){
		userService.saveUser(user);
		ResponseEntity<User> responseEntity = new ResponseEntity<>(user,HttpStatus.CREATED);
		return responseEntity;
	}
	
	@PutMapping("/update")
	public ResponseEntity<User> editUser(@RequestBody User user){
		userService.updateUser(user);
		ResponseEntity<User> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") int userId){
		userService.deleteUser(userId);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;
	}
}
