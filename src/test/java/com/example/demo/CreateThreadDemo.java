package com.example.demo;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 多线程（Multi-threaded）的实验（experiment）
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CreateThreadDemo extends Thread {

    public CreateThreadDemo() {
        //设置当前线程名称
        this.setName("MyThread");
    }

    @SneakyThrows
    @Override
    public void run() {
        new CreateThreadDemo().start();
        while (true) {
            printThreadInfo();
            Thread.sleep(1000);
        }
    }

    /**
     * 输出当前线程的信息
     */
    @Test
    public void printThreadInfo() {
        System.out.println(String.format("当前运行的线程名为：%s", Thread.currentThread().getName()));
    }


}
