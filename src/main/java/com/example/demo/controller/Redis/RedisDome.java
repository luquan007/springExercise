package com.example.demo.controller.Redis;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("redisDome")
public class RedisDome {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private JdbcTemplate jdbcTemplate;

    @GetMapping("save")
    public String  testOne(Long id){
        String sql=String.format("select value from ai2022.pro_template_dosage ptd where ptd.id = %s",id);
        Map<String, Object> map = jdbcTemplate.queryForMap(sql);
        String value = map.get("value").toString();
        stringRedisTemplate.opsForValue().set("tagTools:".concat(id.toString().concat("null")),value,24, TimeUnit.HOURS);
        return "success";
    }

    @GetMapping("get")
    public JSONArray get(Long id){
        String key=String.format("tagTools:%s*",id);
        List<String> tagTools = new ArrayList<>( stringRedisTemplate.keys(key));
        System.out.println(tagTools.size());
        for (String tagTool : tagTools) {
            stringRedisTemplate.delete(tagTool);
        }
       /* String s = stringRedisTemplate.opsForValue().get("tagTools:".concat(id.toString()).concat("*"));
        JSONArray objects = JSONArray.parseArray(s);*/
        return null;
    }

}
