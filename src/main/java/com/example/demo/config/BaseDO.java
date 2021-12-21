package com.example.demo.config;

import java.util.Date;

public class BaseDO {
    private String createBy;
    private Date createDate;
    private String modifyBy;
    private Date modifyDate;
    private Long delFlag;

    public BaseDO() {
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getModifyBy() {
        return this.modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return this.modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(Long delFlag) {
        this.delFlag = delFlag;
    }
}
