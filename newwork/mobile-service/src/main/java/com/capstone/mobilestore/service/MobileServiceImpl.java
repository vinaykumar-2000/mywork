package com.capstone.mobilestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.mobilestore.entity.Mobile;
import com.capstone.mobilestore.exception.ResourceNotFoundException;
import com.capstone.mobilestore.repository.MobileRepository;

@Service
public class MobileServiceImpl implements MobileService{
	
	@Autowired
	private MobileRepository mobileRepository;

	@Override
	public List<Mobile> findAllMobiles() {
		return mobileRepository.findAll();
	}

	@Override
	public Mobile findMobileById(Long id) {
	Optional<Mobile> optionalMobile = mobileRepository.findById(id);
	if(optionalMobile.isEmpty()) {
		throw new ResourceNotFoundException("Mobile not Existing with this id : "+id);
	}
	Mobile mobile = optionalMobile.get();
		return mobile;
	}

	@Override
	public Mobile saveMobile(Mobile mobile) {
		return mobileRepository.save(mobile);
	}

	@Override
	public Mobile updateMobile(Long id, Mobile mobileDetails) {
		Optional<Mobile> optionalMobile = mobileRepository.findById(mobileDetails.getId());
		if(optionalMobile.isEmpty()) {
			throw new ResourceNotFoundException("Mobile not existing with this id : "+mobileDetails.getId());
		}
		mobileRepository.save(mobileDetails);
		return mobileDetails;
	}

	@Override
	public void deleteMobile(Long id) {
		Optional<Mobile> optionalMobile = mobileRepository.findById(id);
		if(optionalMobile.isEmpty()) {
			throw new ResourceNotFoundException("Mobile not Existing with this id : "+id);
		}
		Mobile mobile = optionalMobile.get();
		mobileRepository.delete(mobile);
		
	}

	@Override
	public List<Mobile> searchMobilesByBrand(String brand) {
		List<Mobile> mobiles = mobileRepository.searchByBrand(brand);
		return mobiles;
	}

	@Override
	public List<Mobile> searchMobilesByModel(String model) {
		List<Mobile> mobiles = mobileRepository.searchByModel(model);
		return mobiles;
	}

	@Override
	public List<Mobile> searchMobilesByPriceRange(Double priceMin, Double priceMax) {
		List<Mobile> mobiles = mobileRepository.searchByPriceRange(priceMin, priceMax);
		return mobiles;
	}


}
