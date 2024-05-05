package com.abc.userservices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.userservices.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

	Optional<UserEntity> findByUsernameOrEmail(String username, String email);
}