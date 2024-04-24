package com.abc.tktbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.tktbooking.entity.User;
import com.abc.tktbooking.exceptions.ResourceNotFoundException;
import com.abc.tktbooking.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		userRepository.save(user);
		return user;
	}

	@Override
	public User getUserById(int userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isEmpty()) {
			throw new ResourceNotFoundException("User Not Existing with this Id :"+userId);
		}
		User user = optionalUser.get();
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public User updateUser(User user) {
		Optional<User> optionalUser = userRepository.findById(user.getUserId());
		if(optionalUser.isEmpty()) {
			throw new ResourceNotFoundException("User Not Existing with this Id :"+user.getUserId());
		}
		userRepository.save(user);
		return user;
	}

	@Override
	public void deleteUser(int userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isEmpty()) {
			throw new ResourceNotFoundException("User Not Existing with this Id :"+userId);
		}
		User user = optionalUser.get();
		userRepository.delete(user);
	}

}
