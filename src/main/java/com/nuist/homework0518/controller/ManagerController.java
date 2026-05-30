package com.nuist.homework0518.controller;

import com.nuist.homework0518.entity.Order;
import com.nuist.homework0518.entity.Product;
import com.nuist.homework0518.entity.User;
import com.nuist.homework0518.server.ManagerServer;
import com.nuist.homework0518.server.impl.ManagerServerImpl;
import com.nuist.homework0518.view.*;

import java.util.List;

public class ManagerController {

    private  static ManagerServer managerServer = new ManagerServerImpl();
    public static void managerController(String userChoice){
        switch (userChoice) {
            case "1":
                UserManagerView.userManagerView();
                break;
            case "2":
                OrderManagerView.orderManagerView();
                break;
            case "3":
                ProductManagerView.productManagerView();
                break;
            case "4":
                AdminManagerController.adminManagerController(userChoice);
                break;
            case "5":
                LoginView.loginView();
            default:
                System.out.println("输入错误");
        }
        ManagerView.managerView();
    }

    public static void checkAllUsers(){
        List<User> users = managerServer.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    public static void checkAllOrders() {
        List<Order> orders = managerServer.getAllOrders();
        for (Order order : orders){
            System.out.println(order);
        }
    }

    public void checkAllProducts() {
        List<Product> products = managerServer.getAllProducts();
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
