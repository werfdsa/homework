package com.nuist.homework0518.view;

import com.nuist.homework0518.controller.LoginController;
import com.nuist.homework0518.controller.ManagerController;
import com.nuist.homework0518.server.ManagerServer;
import com.nuist.homework0518.server.impl.ManagerServerImpl;
import com.nuist.homework0518.util.Print;

import java.util.Scanner;

public class ManagerView {
    private static ManagerController managerController = new ManagerController();

    public static void managerView(){
        Print.print("欢迎来到管理员界面");
        Print.print("1.管理用户");
        Print.print("2.管理订单");
        Print.print("3.管理商品");
        Print.print("4.添加管理员");
        Scanner sc = new Scanner(System.in);
        String userChoice = sc.nextLine();
        ManagerController.managerController(userChoice);
    }


}
