package com.nuist.homework0518.controller;

import com.nuist.homework0518.util.Print;
import com.nuist.homework0518.view.LoginView;

public class LoginController {
    public static void loginController(String userChoice){
        switch (userChoice){
            case "1":
                LoginView.userLoginView();
                break;
            case "2":
                LoginView.adminLoginView();
                break;
            case "3":
                LoginView.userRegister();
                break;
            default:
                Print.print("你输入了错误的指令，请重新输入");
                LoginView.loginView();
        }
    }

    public static void login(String username, String password){
        boolean result;
    }
}
