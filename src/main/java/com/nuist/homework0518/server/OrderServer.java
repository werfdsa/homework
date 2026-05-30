package com.nuist.homework0518.server;

import com.nuist.homework0518.entity.Product;

import java.util.List;
import java.util.Map;

public interface OrderServer {
    /**
     * 线程安全下单
     * @param userId   用户ID
     * @param products key=商品ID, value=数量
     * @return 订单ID，失败返回 -1
     */
    int placeOrder(int userId, Map<Integer, Integer> products);

    /** 获取所有在售商品 */
    List<Product> getAvailableProducts();
}
