package com.mengla.bookstore.service.impl;

import com.mengla.bookstore.dao.ICartDao;
import com.mengla.bookstore.model.Cart;
import com.mengla.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService implements ICartService {

    @Autowired
    private ICartDao cartDao;

    @Override
    public List<Cart> findCarts() {
        return cartDao.findCarts();
    }

    @Override
    public List<Cart> findCartsByUserId(long userId) {
        return cartDao.findCartsByUserId(userId);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public int insertCarts(List<Cart> carts) {
        if (carts == null){
            return 0;
        }else {
            int row = 0;
            for (int i=0;i<carts.size();i++ ){
                if (cartDao.findCart(carts.get(i).getUserId(),carts.get(i).getBookId())==null){
                    row += cartDao.insertCart(carts.get(i));
                }else {
                    return 0;
                }
            }
            return row;
        }
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public int insertCart(Cart cart) {
        if (cartDao.findCart(cart.getUserId(),cart.getBookId())==null){
            return cartDao.insertCart(cart);
        }else {
            return 0;
        }
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public int updateCart(Cart cart) {
        return cartDao.updateCart(cart);
    }

    @Override
    public int deleteCart(long userId, long bookId) {
        return cartDao.deleteCart(userId,bookId);
    }

    @Override
    public Cart findCart(long userId,long bookId) {
        return cartDao.findCart(userId,bookId);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public int deleteCarts(List<Cart> carts) {
        if (carts!=null || !carts.isEmpty()){
            for (int i = 0; i<carts.size();i++){
                if (cartDao.deleteCart(carts.get(i).getUserId(),carts.get(i).getBookId())<=0){
                    return 0;
                }
            }
        }
        return 1;
    }
}
