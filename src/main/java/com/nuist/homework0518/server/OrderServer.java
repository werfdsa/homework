package com.nuist.homework0518.server;

import com.nuist.homework0518.entity.Product;

import java.util.List;
import java.util.Map;

public interface OrderServer {

    int placeOrder(int userId, Map<Integer, Integer> products);
    List<Product> getAvailableProducts();
}
