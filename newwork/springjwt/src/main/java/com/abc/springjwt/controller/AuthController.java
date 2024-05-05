package com.abc.springjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.springjwt.entity.UserEntity;
import com.abc.springjwt.model.JwtAuthResponse;
import com.abc.springjwt.model.Login;
import com.abc.springjwt.service.AuthService;
import com.abc.springjwt.service.AuthServiceImpl;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody Login login) {
        String token = authService.login(login);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity userEntity) {
        if (userEntity.getPassword() == null || userEntity.getUsername() == null) {
            return ResponseEntity.badRequest().build();
        }

        UserEntity registeredUser = authService.register(userEntity);
        return ResponseEntity.ok(registeredUser);
    }
}
