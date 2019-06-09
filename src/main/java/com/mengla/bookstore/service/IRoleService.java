package com.mengla.bookstore.service;

import com.mengla.bookstore.model.Role;

import java.util.List;

public interface IRoleService {
    int insertRole(Role role);

    int deleteRoleByRoleName(String roleName);

    int deleteRoleByRoleId(int roleId);

    int updateRole(Role role);

    List<Role> findRole();
}
