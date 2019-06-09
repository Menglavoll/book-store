package com.mengla.bookstore.dao;

import com.mengla.bookstore.model.Role;

import java.util.List;

public interface IRoleDao {

    int insertRole(Role role);

    int deleteRoleByRoleName(String roleName);

    int deleteRoleByRoleId(int roleId);

    int updateRole(Role role);

    List<Role> findRole();
}
