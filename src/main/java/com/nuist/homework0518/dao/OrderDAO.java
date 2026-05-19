package com.nuist.homework0518.dao;

import com.nuist.homework0518.entity.Order;
import com.nuist.homework0518.entity.User;

public interface OrderDAO {
    User findUserByOrderId(int id);
    boolean insertOrder(Order order);
}
