package com.abc.userservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.userservices.config.JwtTokenProvider;
import com.abc.userservices.entity.UserEntity;
import com.abc.userservices.model.JwtAuthResponse;
import com.abc.userservices.model.Login;
import com.abc.userservices.service.AuthService;
import com.abc.userservices.service.AuthServiceImpl;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
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
    
 
    @PostMapping("/users")
    public String saveUser(@RequestBody UserEntity credential) {
    	 String encodedPassword = passwordEncoder.encode(credential.getPassword());
         credential.setPassword(encodedPassword);
         authService.saveUser(credential);

         return "User added to the system";
    }
    
    @PostMapping("/token")
    public String generateToken(@RequestBody Authentication username) {
        return authService.generateToken(username);
    }
    
    @PostMapping("/validateToken")
    public void validateToken(@RequestBody String token) {
        jwtTokenProvider.validateToken(token);
    }
}