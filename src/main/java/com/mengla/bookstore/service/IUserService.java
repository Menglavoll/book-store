package com.mengla.bookstore.service;

import com.mengla.bookstore.model.User;

import java.util.List;

public interface IUserService {
    public List<User> searchUsers();

    public User searchUserById(long userid);

    public List<User> searchUsersByLikeName(String userName);

    public int insertUser(User user);

    public int update(User user);
}
