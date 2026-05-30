package com.nuist.homework0518.controller;

import com.nuist.homework0518.entity.User;
import com.nuist.homework0518.server.impl.LoginServerImpl;
import com.nuist.homework0518.util.Print;
import com.nuist.homework0518.view.LoginView;
import com.nuist.homework0518.view.ManagerView;
import com.nuist.homework0518.view.UserView;

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

    public static void UserLogin(String username, String password) {
        User user = new LoginServerImpl().getUserByUsernameAndPassword(username, password);
        if (user != null) {
            Print.print("登录成功");
            UserView.userView(user.getId());
        } else {
            Print.print("登录失败");
            LoginView.loginView();
        }
    }

    public static void AdminLogin(String username, String password) {
        boolean result = new LoginServerImpl().adminServerLogin(username, password);
        if (result) {
            Print.print("登录成功");
            ManagerView.managerView();
        } else {
            Print.print("登录失败");
            LoginView.loginView();
        }
    }

    public static void UserRegister(String username, String password) {
        boolean result = new LoginServerImpl().userRegister(username, password);
        if (result) {
            Print.print("注册成功");
            LoginView.loginView();
        } else {
            Print.print("注册失败");
            LoginView.loginView();
        }
    }
}
