package com.mengla.bookstore.dao;

import com.mengla.bookstore.model.Order;

import java.util.List;

public interface IOrderDao {

    List<Order> findOrders();

    List<Order> findOrdersByUserId(long userId);

    Order findOrderById(long orderId);

    int insertOrder(Order order);

    int updateOrder(Order order);

    int deleteOrder(long orderId);
}
