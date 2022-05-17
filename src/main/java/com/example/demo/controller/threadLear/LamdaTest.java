package com.example.demo.controller.threadLear;

/**
 * Lamda表达式
 * 使代码更加简洁
 * 六个过程
 * 要求：接口是函数式接口
 */
public class LamdaTest {

    //3、静态内部类
    static class Like2 implements Ilike {
        @Override
        public void lambda() {
            System.out.println("Like2");
        }
    }

    public static void main(String[] args) {
        Ilike ilike = new Like();  //接口调用实现类
        ilike.lambda();


        //4、局部内部类
        class Like3 implements Ilike {
            @Override
            public void lambda() {
                System.out.println("Like3");
            }
        }
        //需要按顺序来
        ilike = new Like3();


        //5、匿名内部类，没有类的名称，必须借助接口或者父类
        ilike = new Ilike() {
            @Override
            public void lambda() {
                System.out.println("匿名内部类！！");
            }
        };

        //6、用lambda简化
        ilike = () -> {
            System.out.println("lambda");
        };
        ilike.lambda();

    }
}

//1、定义一个函数式接口 :任何接口只包含一个抽象方法，那么它就是一个函数式接口
interface Ilike {
    void lambda();
}

//2实现类
class Like implements Ilike {
    @Override
    public void lambda() {
        System.out.println("I Like Lambda");
    }
}