package com.nuist.homework0518.view;

import com.nuist.homework0518.controller.UserManagerController;
import com.nuist.homework0518.util.Print;

import java.util.Scanner;

public class UserManagerView {
    public static void userManagerView(){
        Print.print("用户管理界面");
        Print.print("1.查看所有用户");
        Print.print("2.删除用户");
        Print.print("3.返回上一级");
        Scanner sc = new Scanner(System.in);
        String userChoice = sc.nextLine();
        UserManagerController.userManagerController(userChoice);
    }
}
