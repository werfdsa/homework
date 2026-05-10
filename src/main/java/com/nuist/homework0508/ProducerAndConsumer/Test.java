package com.nuist.homework0508.ProducerAndConsumer;

public class Test {
    public static void main(String[] args) {
        Basket basket = new Basket();
        for (int i = 0; i < 10; i++) {
            new Thread(new ProducerKun(i,basket)).start();
        }
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 10; i++) {
            new Thread(new ConsumerIKun(i,basket)).start();
        }
    }
}
