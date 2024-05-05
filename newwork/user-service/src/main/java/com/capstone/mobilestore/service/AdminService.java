package com.capstone.mobilestore.service;

import java.util.List;

import com.capstone.mobilestore.entity.Admin;

public interface AdminService {

	Admin createAdmin(Admin admin);
    Admin getAdminById(Long id);
    List<Admin> getAllAdmins();
    Admin updateAdmin(Long id, Admin adminDetails);
    void deleteAdmin(Long id);
}
