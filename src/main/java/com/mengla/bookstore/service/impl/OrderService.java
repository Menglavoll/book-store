package com.mengla.bookstore.service.impl;

import com.mengla.bookstore.dao.IOrderDao;
import com.mengla.bookstore.dao.IPackageListDao;
import com.mengla.bookstore.model.Order;
import com.mengla.bookstore.model.PackageList;
import com.mengla.bookstore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderDao orderDao;

    @Autowired
    private IPackageListDao packageListDao;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public int insertOrder(Order order) {
        return orderDao.insertOrder(order);
    }

    @Override
    public int deleteOrder(Long orderId) {
        return orderDao.deleteOrder(orderId);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public int updateOrder(Order order) {
        return orderDao.updateOrder(order);
    }

    @Override
    public Order findOrderById(Long orderId) {
        return orderDao.findOrderById(orderId);
    }

    @Override
    public List<Order> findOrders() {
        return orderDao.findOrders();
    }

    @Override
    public List<Order> findOrdersByUserId(Long userId) {
        return orderDao.findOrdersByUserId(userId);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public int insertOrderAndPackage(Order order, List<PackageList> packageLists) {
        if (orderDao.insertOrder(order)>0){
            for (int i = 0;i<packageLists.size();i++){
                if (packageListDao.insertPackageList(packageLists.get(i)) <= 0){
                    return 0;
                }
            }
        }
        return 1;
    }

    @Override
    public Order findOrderByOrder(int orderPackage) {
        return orderDao.findOrderByOrder(orderPackage);
    }
}
