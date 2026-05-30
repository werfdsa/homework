package com.nuist.homework0518.view;

import com.nuist.homework0518.controller.ManagerController;
import com.nuist.homework0518.util.Print;

import java.util.Scanner;

public class AdminManagerView {
    public static void adminManagerView(){
        Print.print("1.添加管理员");
        Print.print("2.返回上一级");
        Scanner sc = new Scanner(System.in);
        String userChoice = sc.nextLine();
        ManagerController.managerController(userChoice);
    }
}
