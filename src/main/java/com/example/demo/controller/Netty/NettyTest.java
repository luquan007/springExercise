package com.example.demo.controller.Netty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("nettyTest")
public class NettyTest {

    @Resource
    private MyClient myClient;

    @Resource
    private MyServer myServer;

    @GetMapping("one")
    public String one(String sendMsg){

        return null;
    }

}
