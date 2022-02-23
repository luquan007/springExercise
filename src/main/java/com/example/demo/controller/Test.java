package com.example.demo.controller;

import com.example.demo.utils.DomeTimerTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

@RestController
@RequestMapping("/test")
public class Test {

    @GetMapping("timerTask")
    public Map timerTaskTest(@RequestBody Map map1){
        /*Timer timer = new Timer();
        timer.schedule(new DomeTimerTask("PeriodDemo"),1000L,1000L*10);*/
        Map map = new HashMap<Long,String>();
        map.put(1,"1");
        map.put(2,"2");
        map.put(3,"3");
        map.put(4,"4");
        map.put(5,"5");
        return map1;
    }
}
