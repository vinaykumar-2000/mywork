package com.capstone.mobilestore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstone.mobilestore.entity.Role;
import com.capstone.mobilestore.entity.User;
import com.capstone.mobilestore.exception.ResourceNotFoundException;
import com.capstone.mobilestore.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	 public User registerAdmin(String username, String password) {
        if (!isValidPassword(password)) {
            throw new ResourceNotFoundException("Password does not meet the requirements");
        }
        return registerUser(username, password, Role.ADMIN);
    }

	@Override
    public User registerCustomer(String username, String password) {
        if (!isValidPassword(password)) {
            throw new ResourceNotFoundException("Password does not meet the requirements");
        }
        return registerUser(username, password, Role.CUSTOMER);
    }

    // Custom method to validate password
    private boolean isValidPassword(String password) {
        // Password regex pattern
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        return password.matches(passwordPattern);
    }
	
	private User registerUser(String username, String password, Role role) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalStateException("Username already exists!");
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole(role);
        return userRepository.save(newUser);
    }

	@Override
	public User findUserNameById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser.isEmpty()) {
			throw new ResourceNotFoundException("User not Existing by id :"+id);
		}
		User user = optionalUser.get();
		return userRepository.save(user);
	}

}
