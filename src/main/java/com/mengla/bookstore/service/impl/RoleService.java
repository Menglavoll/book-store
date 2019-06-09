package com.mengla.bookstore.service.impl;

import com.mengla.bookstore.dao.IRoleDao;
import com.mengla.bookstore.model.Role;
import com.mengla.bookstore.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public int insertRole(Role role) {
        return roleDao.insertRole(role);
    }

    @Override
    public int deleteRoleByRoleName(String roleName) {
        return roleDao.deleteRoleByRoleName(roleName);
    }

    @Override
    public int deleteRoleByRoleId(int roleId) {
        return roleDao.deleteRoleByRoleId(roleId);
    }

    @Override
    public int updateRole(Role role) {
        return roleDao.updateRole(role);
    }

    @Override
    public List<Role> findRole() {
        return roleDao.findRole();
    }
}
