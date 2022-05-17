package com.example.demo.controller.threadLear;


//1.实现runnable接口的实现类对象
//2.重写run（）方法
//3.调用Start（）

public class RunnableTest implements Runnable {

    @Override
    public void run() {
        //run方法的主体线程
        for (int i = 0; i < 200; i++) {
            System.out.println("coding");
        }

    }

    public static void main(String[] args) {
        RunnableTest runnableTest = new RunnableTest();
        Thread thread = new Thread();
        thread.start();
    }
}
