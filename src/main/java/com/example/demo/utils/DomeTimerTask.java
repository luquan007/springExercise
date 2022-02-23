package com.example.demo.utils;

import com.baomidou.mybatisplus.annotation.TableId;
import com.example.demo.service.LampService;
import com.example.demo.service.impl.LampServiceImpl;
import lombok.Data;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DomeTimerTask extends TimerTask {

    @Resource
    private LampServiceImpl lampService;

    private String taskName;

    public DomeTimerTask(String taskName){
        this.taskName= taskName;
    }

    @Override
    public void run() {
        Long status = lampService.status(1l);
        System.out.println(new Date() + " : 任务「" + taskName + "」被执行。");
    }
}

