package com.mengla.bookstore.dao;

import com.mengla.bookstore.model.User;

import java.util.List;

public interface IUserDao {
    List<User> searchUsers();

    User searchUserById(long userid);

    List<User> searchUsersByLikeName(String username);

    int insertUser(User user);
    
    int update(User user);

    User searchUserByMobile(String mobile);
}
