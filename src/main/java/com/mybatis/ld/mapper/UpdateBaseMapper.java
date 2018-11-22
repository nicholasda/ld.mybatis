package com.mybatis.ld.mapper;

import org.apache.ibatis.annotations.UpdateProvider;
import com.mybatis.ld.example.update.UpdateBaseExample;
import com.mybatis.ld.provider.MyUpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

@RegisterMapper
public interface UpdateBaseMapper {

    @UpdateProvider(type = MyUpdateProvider.class, method = "dynamicSQL")
    public int updateObject(UpdateBaseExample baseExample);
}
