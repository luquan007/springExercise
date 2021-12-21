package com.example.demo.controller;


import com.example.demo.service.LampService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author luQuan
 * @since 2021-12-10
 */
@RestController
@RequestMapping("/lamp")
public class LampController {

    @Resource
    private LampService lampService;

    @GetMapping("on")
    public String on(Long id) {
        return lampService.on(id);
    }

    @GetMapping("off")
    public String off(Long id) {
        return lampService.off(id);
    }

    @GetMapping("status")
    public Long status(Long id){
        return lampService.status(id);
    }

}
