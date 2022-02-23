package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Antlia2tbltext;
import com.example.demo.entity.Lamp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SqlMapper extends BaseMapper<Antlia2tbltext> {
    List<Antlia2tbltext> getList(Long id);
}
