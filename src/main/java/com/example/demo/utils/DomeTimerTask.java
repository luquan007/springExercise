package com.example.demo.utils;

import com.baomidou.mybatisplus.annotation.TableId;
import com.example.demo.service.LampService;
import com.example.demo.service.impl.LampServiceImpl;
import lombok.Data;
import lombok.SneakyThrows;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DomeTimerTask extends TimerTask {

    @Resource
    private LampServiceImpl lampService;

    private String taskName;
    private Long a;

    public DomeTimerTask(String taskName, Long a) {
        this.taskName = taskName;
        this.a = a;
    }

    @SneakyThrows
    @Override
    public void run() {
        a++;
        if (a == 10) {
            System.out.println(" 暂停10秒");
            Thread.sleep(1000L * 10);
        }
        System.out.println(new Date() + " : 任务「" + taskName + a + "」被执行。");
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        try {
            timer.schedule(new DomeTimerTask("PeriodDemo", 1l), 1000L, 1000L * 1);
        } catch (Exception e) {

            timer.cancel();
            e.printStackTrace();
        }
    }
}

