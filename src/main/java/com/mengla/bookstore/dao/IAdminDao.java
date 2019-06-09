package com.mengla.bookstore.dao;

import com.mengla.bookstore.model.Admin;

import java.util.List;

public interface IAdminDao {

    int insertAdmin(Admin admin);

    int updateAdmin(Admin admin);

    int deleteAdmin(long adminId);

    List<Admin> findAdmins();

    Admin findAdminById(long adminId);

    Admin findAdminByMobile(String mobile);
}
