package com.mengla.bookstore.service.impl;

import com.mengla.bookstore.dao.IUserDao;
import com.mengla.bookstore.model.User;
import com.mengla.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User searchUserByMobile(String mobile){
        return userDao.searchUserByMobile(mobile);
    }

    @Override
    public List<User> searchUsers() {
        return userDao.searchUsers();
    }

    @Override
    public User searchUserById(long userid) {
        return userDao.searchUserById(userid);
    }

    @Override
    public List<User> searchUsersByLikeName(String username) {
        return userDao.searchUsersByLikeName(username);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public int insertUser(User user) {
        if (user!=null){
            if (userDao.searchUserByMobile(user.getMobile())!=null){
                return 0;
            }else {
                return userDao.insertUser(user);
            }
        }else {
            return 0;
        }
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }
}
