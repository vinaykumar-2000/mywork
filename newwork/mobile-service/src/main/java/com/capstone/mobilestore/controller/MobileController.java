package com.capstone.mobilestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.mobilestore.entity.Mobile;
import com.capstone.mobilestore.service.MobileServiceImpl;

@RestController
@RequestMapping("/mobile")
public class MobileController {

	@Autowired
	private MobileServiceImpl mobileServiceImpl;
	
	@GetMapping("/all")
	public List<Mobile> fetchAllMobiles(){
		return mobileServiceImpl.findAllMobiles();
	}
	
	@PostMapping("/addMobile")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Mobile> addMobile(@RequestBody Mobile mobile){
		mobileServiceImpl.saveMobile(mobile);
		ResponseEntity<Mobile> responseEntity = new ResponseEntity<>(mobile,HttpStatus.CREATED);
		return responseEntity;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Mobile> fetchMobileDetails(@PathVariable("id") Long id) {
		Mobile mobile = mobileServiceImpl.findMobileById(id);
		return new ResponseEntity<>(mobile,HttpStatus.OK);
	}
	
	@PutMapping("/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Mobile> editMobile(@RequestBody Mobile mobile) {
		mobileServiceImpl.saveMobile(mobile);
		ResponseEntity<Mobile> responseEntity = new ResponseEntity<>(mobile,HttpStatus.OK);
		return responseEntity;
	
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteMobile(@PathVariable("id") Long id) {
		mobileServiceImpl.deleteMobile(id);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/search/brand")
    public ResponseEntity<List<Mobile>>  searchByBrand(@RequestParam String brand) {
        List<Mobile> mobiles = mobileServiceImpl.searchMobilesByBrand(brand);
        return new ResponseEntity<>(mobiles,HttpStatus.OK);
    }
	
	@GetMapping("/search/model")
    public ResponseEntity<List<Mobile>> searchByModel(@RequestParam String model) {
        List<Mobile> mobiles = mobileServiceImpl.searchMobilesByModel(model);
        return new ResponseEntity<>(mobiles,HttpStatus.OK);
    }

    @GetMapping("/search/price-range")
    public ResponseEntity<List<Mobile>> searchByPriceRange(@RequestParam Double min, @RequestParam Double max) {
        List<Mobile> mobiles = mobileServiceImpl.searchMobilesByPriceRange(min, max);
        return new ResponseEntity<>(mobiles,HttpStatus.OK);
    }
}
