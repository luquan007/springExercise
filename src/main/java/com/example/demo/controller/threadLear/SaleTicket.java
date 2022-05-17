package com.example.demo.controller.threadLear;


//多线程会导致数据混乱的问题
public class SaleTicket implements Runnable {
    private Long ticketNums = 100l;

    @Override
    public void run() {
        while (true) {
            if (ticketNums <= 0) {
                break;
            }
            //Thread.currentThread() 获取线程本身的方法
            System.out.println(Thread.currentThread().getName() + "--》拿到了第" + ticketNums + "张票");
            ticketNums--;
        }
    }

    public static void main(String[] args) {
        SaleTicket saleTicket = new SaleTicket();
        new Thread(saleTicket, "1").start();
        new Thread(saleTicket, "2").start();

    }
}
