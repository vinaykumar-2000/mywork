package com.capstone.mobilestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.mobilestore.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{
	 boolean existsByUsername(String username);
	 
}
