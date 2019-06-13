package com.mengla.bookstore.service;

import com.mengla.bookstore.model.Order;
import com.mengla.bookstore.model.PackageList;

import java.util.List;

public interface IOrderService {

    int insertOrder(Order order);

    int deleteOrder(Long orderId);

    int updateOrder(Order order);

    Order findOrderById(Long orderId);

    List<Order> findOrders();

    List<Order> findOrdersByUserId(Long userId);

    int insertOrderAndPackage(Order order, List<PackageList> packageLists);

    Order findOrderByOrder(int orderPackage);
}
