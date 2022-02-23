package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.demo.config.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode()
public class Antlia2tbltext  {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long number;

    private String lang;

    private String batch;

    private String msgid;

    private String transcription;

    private Integer count;

    private String type;

    private String gettime;

    private Long vendor;

}
