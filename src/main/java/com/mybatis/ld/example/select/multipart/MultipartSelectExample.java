package com.mybatis.ld.example.select.multipart;

import com.mybatis.ld.example.select.SelectBaseExample;
import com.mybatis.ld.exception.ExampleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultipartSelectExample<T extends MultipartSelectExample<?>> extends SelectBaseExample<T> {

    Logger log = LoggerFactory.getLogger(MultipartSelectExample.class);

    //判断是否使用leftJoin功能
    private boolean leftJoinFlag = false;

    //判断是否使用innerJoin功能
    private boolean innerJoinFlag = false;

    //判断是否使用rightJoin功能
    private boolean rightJoinFlag = false;

    /**
     * 构造方法 带别名
     *
     * @param tableName 表名
     * @param alias     表别名
     */
    public MultipartSelectExample(String tableName, String alias) {
        super(tableName, alias);
        super.setUseMultipart(true);
    }

    /**
     * 构造方法 不带别名
     *
     * @param tableName 表名
     */
    public MultipartSelectExample(String tableName) {
        super(tableName);
        super.setUseMultipart(true);
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

    /**
     * 分组字段 类似于(group by field)
     *
     * @param field 要分组的字段名
     * @return MultipartSelectExample对象
     */
    @Override
    public T groupBy(String field) {
        super.groupBy(field);
        return getThis();
    }

    /**
     * 增加排序字段 类似于(order by dield)
     *
     * @param field     要排序的字段名
     * @param orderType 排序方式，使用ExampleConstants这个常量类中的定义
     * @return MultipartSelectExample对象
     */
    @Override
    public T orderBy(String field, String orderType) {
        super.orderBy(field, orderType);
        return getThis();
    }

    /**
     * 使用left join功能，构造方法传入的表在左，此处传入的表在右 使用left join功能时，构造方法中必须同时传入表名和别名
     *
     * @param tableName 表名
     * @param alias     别名
     * @param on        链接条件
     * @return MultipartSelectExample对象
     */
    public T addLeftJoin(String tableName, String alias, String on) {
        //更改leftJoin标识为true  ：启用状态
        this.leftJoinFlag = true;
        super.addTableName(tableName);
        super.addAlias(alias, tableName);
        super.addLeftJoinOns(on);
        return getThis();
    }

    /**
     * 使用inner join功能，构造方法传入的表在左，此处传入的表在右
     * 使用inner join功能时，构造方法中必须同时传入表名和别名
     *
     * @param tableName 表名
     * @param alias     别名
     * @param on        链接条件
     * @return MultipartSelectExample对象
     */
    public T addInnerJoin(String tableName, String alias, String on) {
        //更改leftJoin标识为true  ：启用状态
        this.innerJoinFlag = true;
        super.addInnerTableName(tableName);
        super.addInnerAlias(alias, tableName);
        super.addInnerJoinOns(on);
        return getThis();
    }

    /**
     * 使用inner join功能，构造方法传入的表在左，此处传入的表在右
     * 使用inner join功能时，构造方法中必须同时传入表名和别名
     *
     * @param tableName 表名
     * @param alias     别名
     * @param on        链接条件
     * @return MultipartSelectExample对象
     */
    public T addRightJoin(String tableName, String alias, String on) {
        //更改leftJoin标识为true  ：启用状态
        this.rightJoinFlag = true;
        super.addRightTableName(tableName);
        super.addRightAlias(alias, tableName);
        super.addRightJoinOns(on);
        return getThis();
    }

    public boolean isLeftJoinFlag() {
        return leftJoinFlag;
    }

    public void setLeftJoinFlag(boolean leftJoinFlag) {
        this.leftJoinFlag = leftJoinFlag;
    }

    public boolean isInnerJoinFlag() {
        return innerJoinFlag;
    }

    public void setInnerJoinFlag(boolean innerJoinFlag) {
        this.innerJoinFlag = innerJoinFlag;
    }

    public boolean isRightJoinFlag() {
        return rightJoinFlag;
    }

    public void setRightJoinFlag(boolean rightJoinFlag) {
        this.rightJoinFlag = rightJoinFlag;
    }
}
