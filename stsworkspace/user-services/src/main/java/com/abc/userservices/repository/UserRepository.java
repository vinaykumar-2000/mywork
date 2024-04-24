package com.abc.userservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.userservices.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
