package com.abc.userservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.abc.userservices.config.JwtTokenProvider;
import com.abc.userservices.entity.UserEntity;
import com.abc.userservices.model.Login;
import com.abc.userservices.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String login(Login login) {
		 Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
	                login.getUsernameOrEmail(),
	                login.getPassword()
	        ));

	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        String token = jwtTokenProvider.generateToken(authentication);

	        return token;
	}

	@Override
	public UserEntity register(UserEntity userEntity) {
		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
		userEntity.setPassword(passEncoder.encode(userEntity.getPassword()));
		userRepository.save(userEntity);
		return userEntity;
	}

	@Override
    public String saveUser(UserEntity credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        userRepository.save(credential);
        return "user added to the system";
    }

	@Override
    public String generateToken(Authentication username) {
        return jwtTokenProvider.generateToken(username);
    }

	@Override
    public void validateToken(String token) {
    	jwtTokenProvider.validateToken(token);
    }

}