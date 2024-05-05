package com.abc.userservices.service;

import org.springframework.security.core.Authentication;

import com.abc.userservices.entity.UserEntity;
import com.abc.userservices.model.Login;

public interface AuthService {

	 String login(Login login);
	 
	 UserEntity register(UserEntity userEntity);
	 
	 String saveUser(UserEntity credential);
	 
	 String generateToken(Authentication username);
	 
	 void validateToken(String token);
}
