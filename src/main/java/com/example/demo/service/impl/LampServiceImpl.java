package com.example.demo.service.impl;

import com.example.demo.entity.Lamp;
import com.example.demo.mapper.LampMapper;
import com.example.demo.service.LampService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author luQuan
 * @since 2021-12-10
 */
@Service
public class LampServiceImpl extends ServiceImpl<LampMapper, Lamp> implements LampService {

    @Resource
    private LampMapper lampMapper;

    @Override
    public String on(Long id) {
        int result = lampMapper.on(id);
        return result == 0 ? "失败" : "成功";
    }

    @Override
    public String off(Long id) {
        int result = lampMapper.off(id);
        return result == 0 ? "失败" : "成功";
    }

    @Override
    public Long status(Long id) {

        return lampMapper.status(id);
    }
}
