package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.example.demo.config.BaseDO;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author luQuan
 * @since 2021-12-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Lamp extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 场景名称
     */
    private String name;

    /**
     * 状态 0：关  1：开
     */
    private Long status;


}
