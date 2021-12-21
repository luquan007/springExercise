package com.example.demo.mapper;

import com.example.demo.entity.Lamp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luQuan
 * @since 2021-12-10
 */
@Mapper
public interface LampMapper extends BaseMapper<Lamp> {
    int on(Long id);

    int off(Long id);

    Long status(Long id);
}
