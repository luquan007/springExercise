package com.example.demo.controller;

import lombok.SneakyThrows;

public class CreateThreadDemo  extends Thread {

    public CreateThreadDemo(){
        //设置当前线程名称
        this.setName("MyThread");
    }

    @SneakyThrows
    @Override
    public void run() {
        new CreateThreadDemo().start();
        while (true){
            printThreadInfo();
            Thread.sleep(1000);
        }
    }

    /**
     *  输出当前线程的信息
     */
    private static void printThreadInfo(){
        System.out.println(String.format("当前运行的线程名为：%s",Thread.currentThread().getName()));
    }
}
