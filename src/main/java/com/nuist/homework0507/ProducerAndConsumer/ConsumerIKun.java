package com.nuist.homework0507.ProducerAndConsumer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConsumerIKun implements Runnable {
    private int id;
    private Basket basket;

    public ConsumerIKun(Basket basket) {
        this.basket = basket;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            Egg egg = basket.pop();
            System.out.println(this.getId()+"号小黑子吃掉了一个篮子里的蛋：" + egg);
        }
    }
}
