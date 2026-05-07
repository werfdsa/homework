package com.nuist.homework0507.ProducerAndConsumer;

public class Test {
    public static void main(String[] args) {
        Basket basket = new Basket();
//        ProducerKun p = new ProducerKun(basket);
//        ConsumerIKun c = new ConsumerIKun(basket);
//        new Thread(p).start();
//        try {
//            Thread.sleep(8000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        new Thread(c).start();
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
