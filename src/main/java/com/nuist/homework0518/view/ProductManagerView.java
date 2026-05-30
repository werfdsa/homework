package com.nuist.homework0518.view;

import com.nuist.homework0518.controller.ProductManagerController;
import com.nuist.homework0518.controller.UserManagerController;
import com.nuist.homework0518.util.Print;

import java.util.Scanner;

public class ProductManagerView {
    public static void productManagerView() {
        Print.print("商品管理界面");
        Print.print("1.查看所有商品");
        Print.print("2.添加商品");
        Print.print("3.更新商品库存");
        Print.print("4.删除商品");
        Print.print("5.返回上一级");
        Scanner sc = new Scanner(System.in);
        String userChoice = sc.nextLine();
        ProductManagerController.productManagerController(userChoice);
    }
}
