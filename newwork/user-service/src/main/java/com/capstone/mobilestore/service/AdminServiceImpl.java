package com.capstone.mobilestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.mobilestore.entity.Admin;
import com.capstone.mobilestore.exception.ResourceNotFoundException;
import com.capstone.mobilestore.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin createAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public Admin getAdminById(Long id) {
		Optional<Admin> optionalAdmin = adminRepository.findById(id);
		if(optionalAdmin.isEmpty()) {
			throw new ResourceNotFoundException("Admin not Existing with this id : "+id);
		}
		Admin admin = optionalAdmin.get();
			return admin;
	}

	@Override
	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}

	@Override
	public Admin updateAdmin(Long id, Admin adminDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAdmin(Long id) {
		if (!adminRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found with ID: " + id);
        }
        adminRepository.deleteById(id);
		
		
	}

}
