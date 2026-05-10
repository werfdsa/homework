package com.nuist.homework0508.javabasicshow.printAB;

public class Print {
    char c = 'B';

    public void printA() {
        if (c != 'A') {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(c);
        c = 'B';
        this.notify();
    }

    public void printB() {
        if (c != 'B') {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(c);
        c = 'A';
        this.notify();
    }
}
