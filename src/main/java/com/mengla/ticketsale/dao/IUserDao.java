package com.mengla.ticketsale.dao;

import com.mengla.ticketsale.model.User;

import java.util.List;

public interface IUserDao {
    public List<User> searchUsers();

    public User searchUserById(int userid);

    public List<User> searchUsersByLikeName(String username);

    public int insertUser(User user);
    
    public int update(User user);
}
