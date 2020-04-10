package com.mybatis.ld.example.update;

import java.util.ArrayList;
import java.util.List;

import com.mybatis.ld.example.BaseExample;
import com.mybatis.ld.exception.ExampleException;
import com.mybatis.ld.exception.example.NoUpdateFieldException;

public class UpdateBaseExample<T extends UpdateBaseExample<?>> extends BaseExample<T> {

    private List<String> updateFields;
    private List<Object> updateValue;

    public UpdateBaseExample(String tableName) {
        super.addTableName(tableName);
    }

    /**
     * @throws ExampleException 异常
     */
    @Override
    public void end() throws ExampleException {
        super.end();
        //判断更新时是否有传入更新字段，否则抛出异常
        if (updateFields == null || updateFields.size() == 0) {
            throw new NoUpdateFieldException("使用UpdateBaseExample时更新字段不能为空！");
        } else {
            if (updateFields.size() != updateValue.size()) {
                throw new NoUpdateFieldException("使用UpdateBaseExample时更新字段个数应和更新的值个数一致！");
            }
        }
    }

    /**
     * 增加字段方法 类似于sql语句的 set field = newValue
     *
     * @param field 字段名
     * @param newValue 新值
     * @return UpdateBaseExample对象
     */
    public T addUpdateField(String field, Object newValue) {
        if (this.updateFields == null) {
            this.updateFields = new ArrayList<>();
            this.updateValue = new ArrayList<>();
        }
        this.updateFields.add(field);
        this.updateValue.add(newValue);
        return getThis();
    }

    public List<String> getUpdateFields() {
        return updateFields;
    }

    public void setUpdateFields(List<String> updateFields) {
        this.updateFields = updateFields;
    }

    public List<Object> getUpdateValue() {
        return updateValue;
    }

    public void setUpdateValue(List<Object> updateValue) {
        this.updateValue = updateValue;
    }
}
