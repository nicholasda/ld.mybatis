package com.mybatis.ld.mapper;

import com.mybatis.ld.example.BaseExample;
import com.mybatis.ld.provider.MySelectProvider;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

@RegisterMapper
public interface SelectBaseMapper<T> {

    @SelectProvider(type = MySelectProvider.class, method = "dynamicSQL")
    List<T> selectPage(BaseExample baseExample);

    @SelectProvider(type = MySelectProvider.class, method = "dynamicSQL")
    T selectEntity(BaseExample baseExample);
}