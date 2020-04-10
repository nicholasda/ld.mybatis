package com.mybatis.ld.mapper;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author satant
 */
public interface BaseExampleMapper<T> extends Mapper<T>, SelectBaseMapper<T>, UpdateBaseMapper {

}
