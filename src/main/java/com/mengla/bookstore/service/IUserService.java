package com.mengla.bookstore.service;

import com.mengla.bookstore.model.User;

import java.util.List;

public interface IUserService {

    User searchUserByMobile(String mobile);

    List<User> searchUsers();

    User searchUserById(long userid);

    List<User> searchUsersByLikeName(String userName);

    int insertUser(User user);

    int update(User user);
}
