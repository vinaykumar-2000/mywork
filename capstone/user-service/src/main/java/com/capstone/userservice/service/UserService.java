package com.capstone.userservice.service;

import com.capstone.userservice.entity.User;


public interface UserService {

	User registerAdmin(String username, String password);
    User registerCustomer(String username, String password);
    User findUserNameById(Long id);
	
}
