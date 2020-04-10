package com.mybatis.ld.example.update.multipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mybatis.ld.example.update.UpdateBaseExample;
import com.mybatis.ld.exception.ExampleException;

public class MultipartUpdateExample<T extends MultipartUpdateExample<?>> extends UpdateBaseExample<T> {

    Logger log = LoggerFactory.getLogger(MultipartUpdateExample.class);

    public MultipartUpdateExample(String tableName) {
        super(tableName);
    }

    @Override
    public T addUpdateField(String field, Object newValue) {
        super.addUpdateField(field, newValue);
        return getThis();
    }

    /**
     * 开始查询前结束时调用的检查方法
     * 
     * @throws ExampleException 异常
     */
    @Override
    public void end() throws ExampleException {
        super.end();
    }
}
