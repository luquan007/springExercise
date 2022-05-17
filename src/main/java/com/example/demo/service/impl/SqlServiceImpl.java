package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Antlia2tbltext;
import com.example.demo.mapper.SqlMapper;
import com.example.demo.service.SqlService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SqlServiceImpl extends ServiceImpl<SqlMapper, Antlia2tbltext> implements SqlService {

    @Resource
    private SqlMapper sqlMapper;

    @Override
    public String getList(Long id) {
        List<Antlia2tbltext> result = sqlMapper.getList(id);
        for (Antlia2tbltext antlia2tbltext : result) {

        }
        return null;
    }
}
