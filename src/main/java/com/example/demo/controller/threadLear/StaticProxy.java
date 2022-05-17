package com.example.demo.controller.threadLear;

/**
 * 静态代理类
 * 真实对象和diali
 */
public class StaticProxy {
    public static void main(String[] args) {
        Wedding wedding = new Wedding(new you());
        wedding.HappyMarry();
    }
}

interface Marry {
    void HappyMarry();
}

class you implements Marry {

    @Override
    public void HappyMarry() {
        System.out.println("开始真实的运行");
    }
}

//代理角色
class Wedding implements Marry {
    private Marry target;

    public Wedding(Marry target) {
        this.target = target;
    }

    @Override
    public void HappyMarry() {
        before();
        this.target.HappyMarry();
        after();
    }

    private void before() {
        System.out.println("开始之前！");
    }

    private void after() {
        System.out.println("开始之后");
    }

}
