package com.mengla.ticketsale.service;

import com.mengla.ticketsale.model.User;

import java.util.List;

public interface IUserService {
    public List<User> searchUsers();

    public User searchUserById(int userid);

    public List<User> searchUsersByLikeName(String userName);

    public int insertUser(User user);

    public int update(User user);
}
