package com.capstone.mobilestore.service;

import com.capstone.mobilestore.entity.User;


public interface UserService {

	User registerAdmin(String username, String password);
    User registerCustomer(String username, String password);
    User findUserNameById(Long id);
	
}
