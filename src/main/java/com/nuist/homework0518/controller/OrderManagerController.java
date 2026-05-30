package com.nuist.homework0518.controller;

import com.nuist.homework0518.entity.Order;
import com.nuist.homework0518.entity.OrderStatus;
import com.nuist.homework0518.server.ManagerServer;
import com.nuist.homework0518.server.impl.ManagerServerImpl;
import com.nuist.homework0518.view.ManagerView;
import com.nuist.homework0518.view.OrderManagerView;

import java.util.List;
import java.util.Scanner;

public class OrderManagerController {
    private static ManagerServer managerServer = new ManagerServerImpl();
    public static void orderManagerController(String userChoice){
        Scanner sc = new Scanner(System.in);
        switch (userChoice) {
            case "1":
                List<Order> orders = managerServer.getAllOrders();
                for (Order order : orders){
                    System.out.println(order);
                }
                break;
            case "2":
                System.out.println("请输入你想修改的订单id");
                int id = sc.nextInt();
                System.out.println("请输入你想修改的订单状态:Pending,paid,completed,cancelled");
                String status = sc.next();
                managerServer.modifyOrder(id, OrderStatus.valueOf(status));
                break;
            case "3":
                System.out.println("请输入你想删除的订单id");
                id = sc.nextInt();
                managerServer.deleteOrder(id);
                break;
            case "4":
                ManagerView.managerView();
                break;
            default:
                System.out.println("输入错误");
                OrderManagerView.orderManagerView();
        }
        ManagerView.managerView();
    }
}
