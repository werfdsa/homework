package com.nuist.homework0508.ProducerAndConsumer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProducerKun implements Runnable{
    private int id;
    private Basket basket;
    public ProducerKun(Basket basket){
        this.basket = basket;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            Egg egg = new Egg(i);
            basket.push(egg);
            System.out.println(this.getId()+"号哥哥往篮子中放了一个蛋："+egg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
