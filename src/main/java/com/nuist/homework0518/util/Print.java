package com.nuist.homework0518.util;

public class Print {
    public static void print(String msg){
        System.out.println(msg);
    }

    public static void print(){
        for (int i = 0; i < 20; i++) {
            System.out.print('=');
        }
        System.out.println();
    }
}
