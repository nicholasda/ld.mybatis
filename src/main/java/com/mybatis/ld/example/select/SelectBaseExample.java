package com.mybatis.ld.example.select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mybatis.ld.example.BaseExample;
import com.mybatis.ld.exception.ExampleException;

public class SelectBaseExample<T extends SelectBaseExample<?>> extends BaseExample<T> {

    Logger log = LoggerFactory.getLogger(SelectBaseExample.class);
    private Map<String, Object> order;
    private String groupBy;

    //此处的fields一般是表字段名的集合，但是在特殊的情况下，例如求和时可以是   sum(字段名)
    private List<String> fields;

    public SelectBaseExample(String tableName, String alias, Map<String, Object> orderBy, String groupBy) {
        super.setUseMultipart(false);
        super.addTableName(tableName);
        super.addAlias(alias, tableName);
        this.order = orderBy;
        this.groupBy = groupBy;
    }

    public SelectBaseExample(String tableName, String alias) {
        super.setUseMultipart(false);
        super.addTableName(tableName);
        super.addAlias(alias, tableName);
    }

    public SelectBaseExample(String tableName) {
        super.setUseMultipart(false);
        super.addTableName(tableName);
    }

    /**
     * 添加返回结果的字段 类似于(select field,field,field )
     *
     * @param field 要添加的字段
     * @return T对象
     */
    public T addField(String field) {
        if (this.fields == null) {
            this.fields = new ArrayList<>();
        }
        this.fields.add(field);
        return getThis();
    }

    /**
     * 添加返回结果的字段 类似于(select field,field,field )
     *
     * @param fields 要添加的字段list集合
     * @return T对象
     */
    public T addField(List fields) {
        if (this.fields == null) {
            this.fields = new ArrayList<>();
        }
        this.fields.addAll(fields);
        return getThis();
    }

    /**
     * 对SQL语句添加排序
     *
     * @param field 要排序的字段名
     * @param orderType 使用ExampleConstants这个常量类
     * @return T对象
     */
    public T orderBy(String field, String orderType) {
        if (this.order == null) {
            this.order = new HashMap<>();
        }
        this.order.put(field, orderType);
        return getThis();
    }

    /**
     * 对SQL语句添加分组
     *
     * @param field 要分组的字段名
     * @return T 对象
     */
    public T groupBy(String field) {
        this.groupBy = field;
        return getThis();
    }

    /**
     * 表示查询语句结束，进行检查的方法
     *
     * @throws ExampleException 异常
     */
    @Override
    public void end() throws ExampleException {
        super.end();
        //判断查询时是否有字段，若没有传入任何字段则默认查询为 "*"
        if (fields == null || fields.size() == 0) {
            log.debug("未传入字段，默认选择查询所有字段！");
            fields = new ArrayList<>();
            fields.add("*");
        }
    }

    public Map<String, Object> getOrder() {
        return order;
    }

    public void setOrder(Map<String, Object> order) {
        this.order = order;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "SelectExample{" + ", orderBy=" + order + ", groupBy=" + groupBy + ", fields=" + fields + '}';
    }
}
