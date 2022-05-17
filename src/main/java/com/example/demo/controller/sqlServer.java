package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Antlia2tbltext;
import com.example.demo.service.LampService;
import com.example.demo.service.SqlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.print.attribute.standard.NumberUp;
import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping("/sqlServer")
public class sqlServer {

    @Resource
    private SqlService sqlService;
    @Resource
    LampService lampService;

    @GetMapping("get")
    public String getData(Long id) {
        return sqlService.getList(id);
    }

    /**
     * 定时器
     * Timer timer = new Timer();
     * timer.schedule(new DomeTimerTask(), 2000L, 1000L * 40);
     */
    public class DomeTimerTask extends TimerTask {
        @Override
        public void run() {
            /*
            Antlia2tbltext number = sqlService.getOne(new QueryWrapper<Antlia2tbltext>().eq("id", id));
            return number.toString();*/

        }
    }

}
