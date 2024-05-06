package com.capstone.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.userservice.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{
	 boolean existsByUsername(String username);
	 
}
