package com.mengla.ticketsale.service.impl;

import com.mengla.ticketsale.dao.IUserDao;
import com.mengla.ticketsale.model.User;
import com.mengla.ticketsale.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public List<User> searchUsers() {
        return userDao.searchUsers();
    }

    @Override
    public User searchUserById(int userid) {
        return userDao.searchUserById(userid);
    }

    @Override
    public List<User> searchUsersByLikeName(String username) {
        return userDao.searchUsersByLikeName(username);
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }
}
