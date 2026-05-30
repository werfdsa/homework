package com.nuist.homework0518.controller;

import com.nuist.homework0518.entity.User;
import com.nuist.homework0518.server.ManagerServer;
import com.nuist.homework0518.server.impl.ManagerServerImpl;
import com.nuist.homework0518.util.Print;
import com.nuist.homework0518.view.ManagerView;
import com.nuist.homework0518.view.UserManagerView;

import java.util.List;
import java.util.Scanner;

public class UserManagerController {
    private static ManagerServer managerServer = new ManagerServerImpl();
    public static void userManagerController(String userChoice){
        switch (userChoice) {
            case "1":
                List<User> users =managerServer.getAllUsers();
                for (User user : users){
                    System.out.println(user);
                }
                break;
            case "2":
                System.out.println("请输入你想删除的用户id");
                Scanner sc = new Scanner(System.in);
                int id = sc.nextInt();
                managerServer.deleteUser(id);
                break;
            case "3":
                ManagerView.managerView();
                break;
            default:
                Print.print("输入错误");
                UserManagerView.userManagerView();
        }
        ManagerView.managerView();

    }
}
