package com.nuist.homework0507.printAB;

public class TestPrintAB {
    public static void main(String[] args) {
        Print p =new Print();
        Thread t1 = new Thread(()->{
            synchronized (p) {
                while (true) {
                    p.printA();
                }
            }
        });
        Thread t2 = new Thread(()->{
            synchronized (p) {
                while (true) {
                    p.printB();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
