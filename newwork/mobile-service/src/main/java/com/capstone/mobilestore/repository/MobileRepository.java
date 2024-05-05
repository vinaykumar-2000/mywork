package com.capstone.mobilestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capstone.mobilestore.entity.Mobile;

public interface MobileRepository extends JpaRepository<Mobile, Long>{

	@Query("SELECT m FROM Mobile m WHERE m.brand = :brand")
    List<Mobile> searchByBrand(String brand);

    @Query("SELECT m FROM Mobile m WHERE m.model = :model")
    List<Mobile> searchByModel(String model);
    
    @Query("SELECT m FROM Mobile m WHERE m.price BETWEEN :priceMin AND :priceMax")
    List<Mobile> searchByPriceRange(Double priceMin, Double priceMax);
}
