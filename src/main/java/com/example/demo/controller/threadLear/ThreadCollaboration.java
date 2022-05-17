package com.example.demo.controller.threadLear;


/**
 * 生产者，消费者，产品，缓冲区
 */
public class ThreadCollaboration {
}

class Producers extends Thread {

}

class Consumers extends Thread {

}

class Apple {
    int id;

    public Apple(int id) {
        this.id = id;
    }
}

class buffer {
    //需要容器大小
    Apple[] apples = new Apple[10];

    //容器计数器
    int count = 0;

    //生产者生产产品
    public void push(Apple apple) {
        if (count == apples.length) {
            //通知消费者停止生产
        }
        apples[count] = apple;
        count++;

    }

    //消费者消费产品
    public Apple pop() {
        if (count == 0) {
            //等待生产者生产
        }
        count--;
        Apple apple = apples[count];

        return apple;
    }


}