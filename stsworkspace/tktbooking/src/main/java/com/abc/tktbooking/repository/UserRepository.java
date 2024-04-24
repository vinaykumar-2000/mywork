package com.abc.tktbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.tktbooking.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
