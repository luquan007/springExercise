package com.example.demo.controller.ioStream;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class propertiesTest {
    public static void main(String[] args) throws IOException {
        //1.创建properties对象
        //2.加载指定配置文件
        //3.显示k-v
        //4.根据key获取对应的值
        Properties properties = new Properties();

        properties.load(new FileReader("src/main/resources/application.yml"));

        properties.list(System.out);
    }


}
