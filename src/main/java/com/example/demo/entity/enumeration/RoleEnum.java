package com.example.demo.entity.enumeration;

import java.util.List;
import java.util.stream.Stream;

public enum RoleEnum {

    ADMIN(1l, "ADMIN","超级管理员"),
    AM(2l,"AM","管理员"),
    PM(3l, "PM","项目经理"),
    TM(4l,"TM","队长"),
    QC(5l,"QC","审核员"),
    TC(6l, "TC","标注员");

    private Long code;
    private String desc;
    private String cnName;

    RoleEnum(Long code, String desc, String cnName) {
        this.code = code;
        this.desc = desc;
        this.cnName=cnName;
    }

    public Long getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getCnName(){
        return cnName;
    }


}
