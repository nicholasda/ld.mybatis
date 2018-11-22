package com.mybatis.ld.mapper;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import com.mybatis.ld.example.BaseExample;
import com.mybatis.ld.provider.MySelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

@RegisterMapper
public interface SelectBaseMapper<T> {

    @SelectProvider(type = MySelectProvider.class, method = "dynamicSQL")
    List<T> selectPage(BaseExample baseExample);

    @SelectProvider(type = MySelectProvider.class, method = "dynamicSQL")
    T selectByEntity(BaseExample baseExample);
}