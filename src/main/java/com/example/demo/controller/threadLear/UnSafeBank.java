package com.example.demo.controller.threadLear;

import sun.security.util.AuthResources_it;

public class UnSafeBank {
    public static void main(String[] args) {
        Account account = new Account(100l, "rainbow");
        bank i = new bank(account, 10l, "i");
        bank you = new bank(account, 10l, "you");
        bank family = new bank(account, 100l, "family");
        i.start();
        you.start();
        family.start();
    }

}

//user
class Account {
    Long money;
    String name;

    public Account(Long money, String name) {
        this.money = money;
        this.name = name;
    }
}

//bank
class bank extends Thread {

    //userInfo
    Account account;

    Long drawMoney;

    int manipulableAmount;

    public bank(Account account, Long drawMoney, String name) {
        super(name);
        this.account = account;
        this.drawMoney = drawMoney;
    }

    @Override
    public void run() {
        //determine if have money
        if (account.money - drawMoney < 0) {
            System.out.println(Thread.currentThread().getName() + "insufficientAmount");
            return;
        }
        account.money -= drawMoney;
        manipulableAmount += drawMoney;
        System.out.println(account.name + "remaining:" + account.money);
        System.out.println(Thread.currentThread().getName() + "mainpulable:" + manipulableAmount);
    }
}
