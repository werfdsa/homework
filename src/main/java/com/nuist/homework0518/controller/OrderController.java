package com.nuist.homework0518.controller;

import com.nuist.homework0518.server.OrderServer;
import com.nuist.homework0518.server.impl.OrderServerImpl;
import com.nuist.homework0518.util.Print;

import java.util.HashMap;
import java.util.Map;

public class OrderController {
    private static final Map<Integer, Integer> cart = new HashMap<>();


    public static void addToCart(int productId, int quantity) {
        cart.merge(productId, quantity, Integer::sum);
        Print.print("已加入购物车");
    }


    public static Map<Integer, Integer> getCart() {
        return new HashMap<>(cart);
    }

    public static void clearCart() {
        cart.clear();
    }

    public static void placeOrder(int userId) {
        if (cart.isEmpty()) {
            Print.print("购物车为空");
            return;
        }

        OrderServer orderServer = new OrderServerImpl();
        int orderId = orderServer.placeOrder(userId, new HashMap<>(cart));
        if (orderId > 0) {
            Print.print("下单成功，订单ID: " + orderId);
            cart.clear();
        } else {
            Print.print("下单失败，库存不足");
        }
    }
}
