package com.mengla.bookstore.service;

import com.mengla.bookstore.model.Admin;

import java.util.List;

public interface IAdminService {

    int insertAdmin(Admin admin);

    int updateAdmin(Admin admin);

    int deleteAdmin(long adminId);

    List<Admin> findAdmins();

    Admin findAdminById(long adminId);

    Admin findAdminByMobile(String mobile);

    int registerAdmin(Admin admin);
}
