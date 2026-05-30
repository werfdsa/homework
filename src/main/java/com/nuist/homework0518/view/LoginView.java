package com.nuist.homework0518.view;

import com.nuist.homework0518.controller.LoginController;
import com.nuist.homework0518.util.Print;

import java.util.Scanner;

public class LoginView {
    public static void loginView(){
        Print.print("商品贩卖系统");
        Print.print();
        Print.print("请选择你要登陆的身份或者马上注册");
        Print.print("1.普通用户");
        Print.print("2.管理员");
        Print.print("3.用户注册");
        Scanner sc = new Scanner(System.in);
        String userChoice = sc.nextLine();
        LoginController.loginController(userChoice);
    }

    public static void userLoginView(){
        Print.print("用户登录");
        Print.print("请输入用户名");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        Print.print("请输入密码");
        String password = sc.nextLine();
        LoginController.UserLogin(username,password);

    }

    public static void adminLoginView(){
        Print.print("管理员登录");
        Print.print("请输入用户名");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        Print.print("请输入密码");
        String password = sc.nextLine();
        LoginController.AdminLogin(username,password);
    }

    public static void userRegister(){

        Print.print("新用户注册");
        Print.print("请输入用户名");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        Print.print("请输入密码");
        String password = sc.nextLine();
        Print.print("请再次输入密码");
        String password1 = sc.nextLine();
        if(password.equals(password1)){
            Print.print("注册成功");
            LoginView.loginView();
        }else {
            Print.print("两次密码不一致,请重新注册");
            LoginView.userRegister();
        }

    }

}
