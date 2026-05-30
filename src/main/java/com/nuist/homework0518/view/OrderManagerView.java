package com.nuist.homework0518.view;

import com.nuist.homework0518.controller.OrderManagerController;
import com.nuist.homework0518.util.Print;

import java.util.Scanner;

public class OrderManagerView {
    public static void orderManagerView(){
        Print.print("订单管理界面");
        Print.print("1.查看所有订单");
        Print.print("2.修改订单");
        Print.print("3.删除订单");
        Print.print("4.返回上一级");
        Scanner sc = new Scanner(System.in);
        String userChoice = sc.nextLine();
        OrderManagerController.orderManagerController(userChoice);
        }
}
