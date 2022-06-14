package com.example.demo.controller.Netty;

import io.netty.channel.ChannelHandlerContext;
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
    private MyClientHandler myClientHandler;

    @Resource
    private MyServerHandler myServerHandler;

    @Resource
    private MyServer myServer;

    @GetMapping("one")
    public String one(String sendMsg){
        ChannelHandlerContext ctx = null;
        myClientHandler.sendMsg(ctx,sendMsg);
        return null;
    }

}
