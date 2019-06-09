package com.mengla.bookstore.dao;

import com.mengla.bookstore.model.User;

import java.util.List;

public interface IUserDao {
    public List<User> searchUsers();

    public User searchUserById(long userid);

    public List<User> searchUsersByLikeName(String username);

    public int insertUser(User user);
    
    public int update(User user);
}
