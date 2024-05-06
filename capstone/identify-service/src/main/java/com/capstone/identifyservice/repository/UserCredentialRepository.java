package com.capstone.identifyservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.identifyservice.entity.UserCredential;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Long>{
	Optional<UserCredential> findByName(String username);
}
