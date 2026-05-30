package com.nuist.homework0518.dao;

import com.nuist.homework0518.entity.Order;
import com.nuist.homework0518.entity.OrderStatus;
import com.nuist.homework0518.entity.User;

import java.util.List;

public interface OrderDAO {
    User findUserByOrderId(int id);
    int insertOrder(Order order);
    List<Order> findAllOrders();
    boolean modifyOrder(int id, OrderStatus status);
}
