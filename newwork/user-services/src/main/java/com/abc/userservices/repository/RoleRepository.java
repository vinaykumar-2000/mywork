package com.abc.userservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.userservices.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{

}
