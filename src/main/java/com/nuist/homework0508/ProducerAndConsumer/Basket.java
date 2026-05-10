package com.nuist.homework0508.ProducerAndConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Basket {
    int index = 0;
    Egg[] arrEgg = new Egg[10];
    Lock lock = new ReentrantLock();
    Condition consumerCon= lock.newCondition();
    Condition producerCon = lock.newCondition();

    public void push(Egg egg){
        lock.lock();
        try{
            while (index == arrEgg.length){
                System.out.println("篮子满了");
                Thread.sleep(2000);
                producerCon.await();
            }
            arrEgg[index++] = egg;
            producerCon.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }

    }

    public Egg pop(){
        lock.lock();
        try{
            while(index == 0) {
                System.out.println("篮子中没有蛋了");
                consumerCon.await();
            }
            consumerCon.signal();
            return arrEgg[--index];
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {

            lock.unlock();
        }
    }
}
