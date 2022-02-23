package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Antlia2tbltext;

public interface SqlService extends IService<Antlia2tbltext> {
    String getList(Long id);
}
