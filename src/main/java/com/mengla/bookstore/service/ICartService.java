package com.mengla.bookstore.service;

import com.mengla.bookstore.model.Cart;

import java.util.List;

public interface ICartService {
    /**
     * 查询所有购物车
     * @return
     */
    List<Cart> findCarts();

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    List<Cart> findCartsByUserId(long userId);

    /**
     * 批量插入
     * @param carts
     * @return
     */
    int insertCarts(List<Cart> carts);

    int insertCart(Cart cart);

    /**
     * 修改数据
     * @param cart
     * @return
     */
    int updateCart(Cart cart);

    /**
     * 删除购物车
     * @param userId
     * @param bookId
     * @return
     */
    int deleteCart(long userId, long bookId);

    Cart findCart(long userId,long bookId);

    int deleteCarts(List<Cart> carts);
}
