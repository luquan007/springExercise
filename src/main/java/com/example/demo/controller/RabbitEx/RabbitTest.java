package com.example.demo.controller.RabbitEx;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.TranscriptionDTO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("rabbitMq")
public class RabbitTest {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("get")
    public void get() {
        Object hello = rabbitTemplate.receiveAndConvert("hello");
        System.out.println(hello.getClass());
        System.out.println(hello);
    }

    @GetMapping("send")
    public void send(String str) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName","LuQuan");
        jsonObject.put("speak",str);
        Object temp = jsonObject;
        rabbitTemplate.convertAndSend("my.test", "test",
                temp
        );
        //  rabbitTemplate.convertAndSend("exchangeName","routName",str );
    }

}
