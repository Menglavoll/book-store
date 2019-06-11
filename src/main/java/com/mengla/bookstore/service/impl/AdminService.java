package com.mengla.bookstore.service.impl;

import com.mengla.bookstore.dao.*;
import com.mengla.bookstore.model.Admin;
import com.mengla.bookstore.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private IAdminDao adminDao;

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IOrderDao orderDao;

    @Autowired
    private IPackageListDao packageListDao;

    @Override
    public int insertAdmin(Admin admin) {
        return adminDao.insertAdmin(admin);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return adminDao.updateAdmin(admin);
    }

    @Override
    public int deleteAdmin(long adminId) {
        return adminDao.deleteAdmin(adminId);
    }

    @Override
    public List<Admin> findAdmins() {
        return adminDao.findAdmins();
    }

    @Override
    public Admin findAdminById(long adminId) {
        return adminDao.findAdminById(adminId);
    }

    @Override
    public Admin findAdminByMobile(String mobile) {
        return adminDao.findAdminByMobile(mobile);
    }

    /**
     * 管理员注册服务
     * @param admin
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public int registerAdmin(Admin admin) {

        Admin findAdminByMobile = adminDao.findAdminByMobile(admin.getMobile());
        Admin findAdminByEmail = adminDao.findAdminByEmail(admin.getEmail());

        if(findAdminByMobile != null || findAdminByEmail != null ){
            return 0;
        }else {
            return adminDao.insertAdmin(admin);
        }
    }
}
