package com.example.demo.service;

import com.example.demo.entity.Lamp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author luQuan
 * @since 2021-12-10
 */
public interface LampService extends IService<Lamp> {

    String on(Long id);

    String off(Long id);

    Long status(Long id);
}
