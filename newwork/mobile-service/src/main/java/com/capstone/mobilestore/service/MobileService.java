package com.capstone.mobilestore.service;

import java.util.List;

import com.capstone.mobilestore.entity.Mobile;

public interface MobileService {

	List<Mobile> findAllMobiles();

    Mobile findMobileById(Long id);

    Mobile saveMobile(Mobile mobile);

    Mobile updateMobile(Long id, Mobile mobileDetails);

    void deleteMobile(Long id);
    
    List<Mobile> searchMobilesByBrand(String brand);
    
    List<Mobile> searchMobilesByModel(String model);
    
    List<Mobile> searchMobilesByPriceRange(Double priceMin, Double priceMax);
}