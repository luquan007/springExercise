package com.example.demo.controller.threadLear;

import sun.security.provider.ConfigFile;

/**
 * 5个状态
 * new()   start()   run()   sleep()   dead()；
 * <p>
 * <p>
 * 常用方法
 */


public class StatusThread {
    private static boolean flag = true;

    public static void main(String[] args) {

        Thread test = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority());
        }, "ticket");
        test.start();



        /* yeild 礼让也许不会成功
        Thread threadA = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"线程开始！");
            Thread.yield();
            System.out.println(Thread.currentThread().getName()+"线程结束！");
        },"A");
        Thread threadB = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"线程开始！");
            Thread.yield();
            System.out.println(Thread.currentThread().getName()+"线程结束！");
        },"B ");
        threadA.start();
        threadB.start();*/

        /* //线程的stop方法不建议使用，使用这种标志的方式来停止线程
        Thread thread = new Thread(() -> {
            int i = 0;
            while (flag) {
                System.out.println("run Thread" + i++);
                if (i == 900) {
                    flag = false;
                }
            }
        });
        thread.start();*/
    }

    public void stop() {
        this.flag = false;
    }

}

class Test implements Runnable {
    @Override
    public void run() {
        System.out.println("hahah");
    }
}
