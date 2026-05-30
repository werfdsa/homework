package com.nuist.homework0518.controller;

import com.nuist.homework0518.entity.Admin;
import com.nuist.homework0518.server.ManagerServer;
import com.nuist.homework0518.server.impl.ManagerServerImpl;
import com.nuist.homework0518.view.AdminManagerView;
import com.nuist.homework0518.view.ManagerView;

import java.util.Scanner;

public class AdminManagerController {
    private static ManagerServer managerServer = new ManagerServerImpl();

    public static void adminManagerController(String userChoice){
        Scanner sc = new Scanner(System.in);
        switch (userChoice) {
            case "1":
                System.out.println("请输入管理员用户名");
                String adminName = sc.nextLine();
                System.out.println("请输入管理员密码");
                String adminPassword = sc.nextLine();
                if(managerServer.addAdmin(adminName,adminPassword)){
                    System.out.println("添加成功");
                }else {
                    System.out.println("添加失败");
                    AdminManagerView.adminManagerView();
                }
                break;
            case "2":
                ManagerView.managerView();
                break;
            default:
                System.out.println("输入错误");
        }
        ManagerView.managerView();
    }
}
