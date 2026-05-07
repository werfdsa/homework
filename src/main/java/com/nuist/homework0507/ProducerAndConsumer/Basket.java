package com.nuist.homework0507.ProducerAndConsumer;

public class Basket {
    int index = 0;
    Egg[] arrEgg = new Egg[10];

    public synchronized void push(Egg egg){
        while (index == arrEgg.length){
            System.out.println("篮子满了");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.notifyAll();
        arrEgg[index++] = egg;
    }

    public synchronized Egg pop(){
        while (index == 0){
            System.out.println("篮子中没蛋了");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.notifyAll();
        return arrEgg[--index];
    }
}
