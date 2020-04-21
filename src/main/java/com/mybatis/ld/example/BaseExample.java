package com.mybatis.ld.example;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mybatis.ld.exception.ExampleException;
import com.mybatis.ld.exception.example.InFiledException;
import com.mybatis.ld.exception.example.LikeFiledException;
import com.mybatis.ld.exception.example.NoTableNameException;

public abstract class BaseExample<T extends BaseExample<?>> implements IExample {
    Logger log = LoggerFactory.getLogger(BaseExample.class);

    private boolean useMultipart;
    //相等条件存储
    private List<String> equalsWhereKey;
    private List<Object> equalsWhereValue;
    //大于条件存储
    private List<String> greaterThanWhereKey;
    private List<Object> greaterThanWhereValue;
    //大于等于条件存储
    private List<String> greaterThanOrEqualToWhereKey;
    private List<Object> greaterThanOrEqualToWhereValue;
    //小于条件存储
    private List<String> lessThanWhereKey;
    private List<Object> lessThanWhereValue;
    //小于等于条件存储
    private List<String> lessThanOrEqualToWhereKey;
    private List<Object> lessThanOrEqualToWhereValue;
    //不等于条件存储
    private List<String> notEqualsWhereKey;
    private List<Object> notEqualsWhereValue;
    //表名集合
    private List<String> tableName;
    //内连接表名集合
    private List<String> innerTableName;
    //右连接表名集合
    private List<String> rightTableName;
    //表名与别名的映射。key为别名，value是表名
    private Map<String, String> tableMap;
    //内连接表名与别名的映射。key为别名，value是表名
    private Map<String, String> innerTableMap;
    //右连接表名与别名的映射。key为别名，value是表名
    private Map<String, String> rightTableMap;
    //别名集合
    private List<String> tableAlias;
    //内连接别名集合
    private List<String> innerTableAlias;
    //右连接别名集合
    private List<String> rightTableAlias;
    //左连接条件集合
    private List<String> leftJoinOns;
    //内连接条件集合
    private List<String> innerJoinOns;
    //右连接条件集合
    private List<String> rightJoinOns;

    //exists条件存储
    private List<String> exists;
    //not exists条件存储
    private List<String> notExists;

    private List<String> inFields;
    private List<Object> inValue1;
    private List<Object> inValue2;
    private List<String> notInFields;
    private List<Object> notInValue1;
    private List<Object> notInValue2;
    private List<String> likeFields;
    private List<String> likeValues;
    private List<String> notLikeFields;
    private List<String> notLikeValues;
    private List<String> isNullFields;
    private List<String> isNotNullFields;

    @Override
    public void end() throws ExampleException {
        //如果表名为空直接抛出异常
        if (tableName == null || tableName.size() == 0) {
            throw new NoTableNameException("使用SelectBaseExample时表名不能为空！");
        }
        //检查“=”查询时的字段与值的数量是不是一致
        if (equalsWhereKey != null && equalsWhereKey.size() > 0) {
            if (equalsWhereKey.size() != equalsWhereValue.size()) {
                throw new IllegalArgumentException("“=”查询的字段与值的数量不一致！");
            }
        }
        //检查“>”查询时的字段与值的数量是不是一致
        if (greaterThanWhereKey != null && greaterThanWhereKey.size() > 0) {
            if (greaterThanWhereKey.size() != greaterThanWhereValue.size()) {
                throw new IllegalArgumentException("“>”查询的字段与值的数量不一致！");
            }
        }
        //检查“<”查询时的字段与值的数量是不是一致
        if (lessThanWhereKey != null && lessThanWhereKey.size() > 0) {
            if (lessThanWhereKey.size() != lessThanWhereValue.size()) {
                throw new IllegalArgumentException("“<”查询的字段与值的数量不一致！");
            }
        }
        //检查“!=”查询时的字段与值的数量是不是一致
        if (notEqualsWhereKey != null && notEqualsWhereKey.size() > 0) {
            if (notEqualsWhereKey.size() != notEqualsWhereValue.size()) {
                throw new IllegalArgumentException("“!=”查询的字段与值的数量不一致！");
            }
        }
        //如果字段为空，则赋默认值*，此步骤在selectBaseExample中执行
        //判断别名映射的数量是否与表名集合数量一致
        if (tableAlias != null) {
            if (tableAlias.size() != tableName.size()) {
                throw new IllegalArgumentException("使用别名时，别名数量与表名不一致，请检查！");
            }
        }
        //判断内连接别名映射的数量是否与表名集合数量一致
        if (innerTableAlias != null) {
            if (innerTableAlias.size() != innerTableName.size()) {
                throw new IllegalArgumentException("使用内连接时，别名数量与表名不一致，请检查！");
            }
        }
        //判断右连接别名映射的数量是否与表名集合数量一致
        if (rightTableAlias != null) {
            if (rightTableAlias.size() != rightTableName.size()) {
                throw new IllegalArgumentException("使用右连接时，别名数量与表名不一致，请检查！");
            }
        }
        if (inFields == null && likeFields == null) {
            if (inValue1 != null && inValue1.size() != 0) {
                throw new InFiledException("在没有inField字段时不允许使用in()方法增加值。");
            }
            return;
        } else if (inFields.size() == 1 && (inValue1 == null || inValue1.size() == 0)) {
            throw new InFiledException("SelectWithInExample类在inFields不为空时，必需有value与其对应！");
        } else if (inFields.size() == 2 && (inValue2 == null || inValue2.size() == 0)) {
            log.debug("inFields中的字段数量与inValue中不符，删除了inFields中第二个字段");
            inFields.remove(1);
        }
        inFields.forEach(str -> {
            if (StringUtils.isBlank(str)) {
                throw new InFiledException("inField字段不能为空。");
            }
        });
        if (notInFields == null && notLikeFields == null) {
            if (notInValue1 != null && notInValue1.size() != 0) {
                throw new InFiledException("在没有notInField字段时不允许使用not in()方法增加值。");
            }
            return;
        } else if (notInFields.size() == 1 && (notInValue1 == null || notInValue1.size() == 0)) {
            throw new InFiledException("SelectWithInExample类在notInFields不为空时，必需有value与其对应！");
        } else if (notInFields.size() == 2 && (notInValue2 == null || notInValue2.size() == 0)) {
            log.debug("notInFields中的字段数量与notInValue中不符，删除了notInFields中第二个字段");
            notInFields.remove(1);
        }
        notInFields.forEach(str -> {
            if (StringUtils.isBlank(str)) {
                throw new InFiledException("notInField字段不能为空。");
            }
        });
        if (likeFields != null && likeValues != null) {
            if (likeFields.size() != likeValues.size()) {
                throw new LikeFiledException("like查询时的字段与值的数量不相等！");
            }
        }
        if ((likeFields == null && likeValues != null) || (likeFields != null && likeValues == null)) {
            throw new IllegalArgumentException("likeField 和 likeValue必须成对出现！！");
        }
        if (notLikeFields != null && notLikeValues != null) {
            if (notLikeFields.size() != notLikeValues.size()) {
                throw new LikeFiledException("not like查询时的字段与值的数量不相等！");
            }
        }
        if ((notLikeFields == null && notLikeValues != null) || (notLikeFields != null && notLikeValues == null)) {
            throw new IllegalArgumentException("notLikeField 和 notLikeValue必须成对出现！！");
        }
    }

    public T getThis() {
        return (T)this;
    }

    /**
     * 链式操作添加in()查新的包含值 调用此方法前应先调用过addInField方法
     *
     * @param inValue 要查找的数值
     * @return getThis()对象
     */
    public T in(String inValue) {
        if (inFields == null) {
            throw new InFiledException("在执行in()方法前请先执行addInField()方法增加要查询的字段。");
        }
        int size = inFields.size();
        if (size == 1) {
            if (this.inValue1 == null || this.inValue1.size() == 0) {
                this.inValue1 = new ArrayList<>();
            }
            this.inValue1.add(inValue);
        } else if (size == 2) {
            if (this.inValue2 == null || this.inValue2.size() == 0) {
                this.inValue2 = new ArrayList<>();
            }
            this.inValue2.add(inValue);
        }
        return getThis();
    }

    /**
     * 链式操作添加in()查新的包含值 调用此方法前应先调用过addInField方法
     *
     * @param inValue 要查找的数值的list集合
     * @return getThis()对象
     */
    public T in(List<String> inValue) {
        if (inFields == null) {
            throw new InFiledException("在执行in()方法前请先执行addInField()方法增加要查询的字段。");
        }
        int size = inFields.size();
        if (size == 1) {
            if (this.inValue1 == null || this.inValue1.size() == 0) {
                this.inValue1 = new ArrayList<>();
            }
            this.inValue1.addAll(inValue);
        } else if (size == 2) {
            if (this.inValue2 == null || this.inValue2.size() == 0) {
                this.inValue2 = new ArrayList<>();
            }
            this.inValue2.addAll(inValue);
        }
        return getThis();
    }

    /**
     * 链式操作添加in()查新的包含值 调用此方法前应先调用过addInField方法
     *
     * @param notInValue 要查找的数值
     * @return getThis()对象
     */
    public T notIn(String notInValue) {
        if (notInFields == null) {
            throw new InFiledException("在执行in()方法前请先执行addNotInField()方法增加要查询的字段。");
        }
        int size = notInFields.size();
        if (size == 1) {
            if (this.notInValue1 == null || this.notInValue1.size() == 0) {
                this.notInValue1 = new ArrayList<>();
            }
            this.notInValue1.add(notInValue);
        } else if (size == 2) {
            if (this.notInValue2 == null || this.notInValue2.size() == 0) {
                this.notInValue2 = new ArrayList<>();
            }
            this.notInValue2.add(notInValue);
        }
        return getThis();
    }

    /**
     * 链式操作添加not in()查新的包含值 调用此方法前应先调用过addNotInField()方法
     *
     * @param inValue 要查找的数值的list集合
     * @return getThis()对象
     */
    public T notIn(List<String> inValue) {
        if (notInFields == null) {
            throw new InFiledException("在执行in()方法前请先执行addNotInField()方法增加要查询的字段。");
        }
        int size = notInFields.size();
        if (size == 1) {
            if (this.notInValue1 == null || this.notInValue1.size() == 0) {
                this.notInValue1 = new ArrayList<>();
            }
            this.notInValue1.addAll(inValue);
        } else if (size == 2) {
            if (this.notInValue2 == null || this.notInValue2.size() == 0) {
                this.notInValue2 = new ArrayList<>();
            }
            this.notInValue2.addAll(inValue);
        }
        return getThis();
    }

    /**
     * 添加in查询所需字段方法 类似 infield in （value1,value2,value3...）
     *
     * @param infield 字段名
     * @return 返回T对象
     */
    public T addInField(String infield) {
        if (this.inFields == null || this.inFields.size() == 0) {
            this.inFields = new ArrayList<>();
        }
        this.inFields.add(infield);
        return getThis();
    }

    /**
     * 添加in查询所需字段方法 类似 notInfield in （value1,value2,value3...）
     *
     * @param notInfield 字段名
     * @return 返回T对象
     */
    public T addNotInField(String notInfield) {
        if (this.notInFields == null || this.notInFields.size() == 0) {
            this.notInFields = new ArrayList<>();
        }
        this.notInFields.add(notInfield);
        return getThis();
    }

    /**
     * 添加like查询所需的字段和包含值 类似： field like "%likeValue%"
     *
     * @param field 字段名
     * @param likeValue 包含值
     * @return 返回T对象
     */
    public T like(String field, String likeValue) {
        if (this.likeFields == null || this.likeFields.size() == 0) {
            this.likeFields = new ArrayList<>();
        }
        if (this.likeValues == null || this.likeValues.size() == 0) {
            this.likeValues = new ArrayList<>();
        }
        this.likeFields.add(field);
        this.likeValues.add(likeValue);
        return getThis();
    }

    /**
     * 添加is null查询所需的字段和包含值 类似： field is null
     *
     * @param field 字段名
     * @return 返回T对象
     */
    public T isNull(String field) {
        if (this.isNullFields == null || this.isNullFields.size() == 0) {
            this.isNullFields = new ArrayList<>();
        }
        this.isNullFields.add(field);
        return getThis();
    }

    /**
     * 添加is not null查询所需的字段和包含值 类似： field is not null
     *
     * @param field 字段名
     * @return 返回T对象
     */
    public T isNotNull(String field) {
        if (this.isNotNullFields == null || this.isNotNullFields.size() == 0) {
            this.isNotNullFields = new ArrayList<>();
        }
        this.isNotNullFields.add(field);
        return getThis();
    }

    /**
     * 添加notLike查询所需的字段和包含值 类似： field not like "%notLikeValue%"
     *
     * @param field 字段名
     * @param notLikeValue 包含值
     * @return 返回T对象
     */
    public T notLike(String field, String notLikeValue) {
        if (this.notLikeFields == null || this.notLikeFields.size() == 0) {
            this.notLikeFields = new ArrayList<>();
        }
        if (this.notLikeValues == null || this.notLikeValues.size() == 0) {
            this.notLikeValues = new ArrayList<>();
        }
        this.notLikeFields.add(field);
        this.notLikeValues.add(notLikeValue);
        return getThis();
    }

    /**
     * EXISTS条件
     *
     * @param condition EXISTS条件
     * @return 返回T对象
     */
    public T exists(String condition) {
        if (this.exists == null) {
            exists = new LinkedList<>();
        }
        exists.add(condition);
        return getThis();
    }

    /**
     * NOT EXISTS条件
     *
     * @param condition NOT EXISTS条件
     * @return 返回T对象
     */
    public T notExists(String condition) {
        if (this.notExists == null) {
            notExists = new LinkedList<>();
        }
        notExists.add(condition);
        return getThis();
    }

    /**
     * 相等的where条件
     *
     * @param field 字段名
     * @param value 值
     * @return 返回T对象
     */
    public T equalsWhere(String field, Object value) {
        if (this.equalsWhereKey == null) {
            equalsWhereKey = new LinkedList<>();
            equalsWhereValue = new LinkedList<>();
        }
        equalsWhereKey.add(field);
        equalsWhereValue.add(value);
        return getThis();
    }

    /**
     * 大于的where条件
     *
     * @param field 字段名
     * @param value 值
     * @return 返回T对象
     */
    public T greaterThanWhere(String field, Object value) {
        if (this.greaterThanWhereKey == null) {
            greaterThanWhereKey = new LinkedList<>();
            greaterThanWhereValue = new LinkedList<>();
        }
        greaterThanWhereKey.add(field);
        greaterThanWhereValue.add(value);
        return getThis();
    }

    /**
     * 大于等于的where条件
     *
     * @param field 字段名
     * @param value 值
     * @return 返回T对象
     */
    public T greaterThanOrEqualToWhere(String field, Object value) {
        if (this.greaterThanOrEqualToWhereKey == null) {
            greaterThanOrEqualToWhereKey = new LinkedList<>();
            greaterThanOrEqualToWhereValue = new LinkedList<>();
        }
        greaterThanOrEqualToWhereKey.add(field);
        greaterThanOrEqualToWhereValue.add(value);
        return getThis();
    }

    /**
     * 小于等于的where条件
     *
     * @param field 字段名
     * @param value 值
     * @return 返回T对象
     */
    public T lessThanOrEqualToWhere(String field, Object value) {
        if (this.lessThanOrEqualToWhereKey == null) {
            lessThanOrEqualToWhereKey = new LinkedList<>();
            lessThanOrEqualToWhereValue = new LinkedList<>();
        }
        lessThanOrEqualToWhereKey.add(field);
        lessThanOrEqualToWhereValue.add(value);
        return getThis();
    }

    /**
     * 小于的where条件
     *
     * @param field 字段名
     * @param value 值
     * @return 返回T对象
     */
    public T lessThanWhere(String field, Object value) {
        if (this.lessThanWhereKey == null) {
            lessThanWhereKey = new LinkedList<>();
            lessThanWhereValue = new LinkedList<>();
        }
        lessThanWhereKey.add(field);
        lessThanWhereValue.add(value);
        return getThis();
    }

    /**
     * 不等于的where条件
     *
     * @param field 字段名
     * @param value 值
     * @return 返回T对象
     */
    public T notEqualsWhere(String field, Object value) {
        if (this.notEqualsWhereKey == null) {
            notEqualsWhereKey = new LinkedList<>();
            notEqualsWhereValue = new LinkedList<>();
        }
        notEqualsWhereKey.add(field);
        notEqualsWhereValue.add(value);
        return getThis();
    }

    /**
     * 增加表名
     *
     * @param tableName 表名
     */
    protected void addTableName(String tableName) {
        if (this.tableName == null) {
            this.tableName = new ArrayList<>();
        }
        this.tableName.add(tableName);
    }

    /**
     * 增加内连接表名
     *
     * @param tableName 表名
     */
    protected void addInnerTableName(String tableName) {
        if (this.innerTableName == null) {
            this.innerTableName = new ArrayList<>();
        }
        this.innerTableName.add(tableName);
    }

    /**
     * 增加右连接表名
     *
     * @param tableName 表名
     */
    protected void addRightTableName(String tableName) {
        if (this.rightTableName == null) {
            this.rightTableName = new ArrayList<>();
        }
        this.rightTableName.add(tableName);
    }

    /**
     * 增加别名和表名的映射关系
     *
     * @param alias 别名
     * @param tableName 表名
     * @return 返回T对象
     */
    public T addAlias(String alias, String tableName) {
        if (this.tableAlias == null) {
            this.tableAlias = new ArrayList<>();
        }
        this.tableAlias.add(alias);
        if (this.tableMap == null) {
            this.tableMap = new HashMap<>();
        }
        this.tableMap.put(alias, tableName);
        return getThis();
    }

    /**
     * 增加内连接别名和表名的映射关系
     *
     * @param alias 别名
     * @param tableName 表名
     * @return 返回T对象
     */
    public T addInnerAlias(String alias, String tableName) {
        if (this.innerTableAlias == null) {
            this.innerTableAlias = new ArrayList<>();
        }
        this.innerTableAlias.add(alias);
        if (this.innerTableMap == null) {
            this.innerTableMap = new HashMap<>();
        }
        this.innerTableMap.put(alias, tableName);
        return getThis();
    }

    /**
     * 增加右连接别名和表名的映射关系
     *
     * @param alias 别名
     * @param tableName 表名
     * @return 返回T对象
     */
    public T addRightAlias(String alias, String tableName) {
        if (this.rightTableAlias == null) {
            this.rightTableAlias = new ArrayList<>();
        }
        this.rightTableAlias.add(alias);
        if (this.rightTableMap == null) {
            this.rightTableMap = new HashMap<>();
        }
        this.rightTableMap.put(alias, tableName);
        return getThis();
    }

    /**
     * 增加左连接条件
     *
     * @param leftJoinOn 左连接条件
     */
    public void addLeftJoinOns(String leftJoinOn) {
        if (this.leftJoinOns == null) {
            this.leftJoinOns = new ArrayList<>();
        }
        this.leftJoinOns.add(leftJoinOn);
    }

    /**
     * 增加内连接条件
     *
     * @param innerJoinOn 内连接条件
     */
    public void addInnerJoinOns(String innerJoinOn) {
        if (this.innerJoinOns == null) {
            this.innerJoinOns = new ArrayList<>();
        }
        this.innerJoinOns.add(innerJoinOn);
    }

    /**
     * 增加右连接条件
     *
     * @param innerJoinOn 内连接条件
     */
    public void addRightJoinOns(String innerJoinOn) {
        if (this.rightJoinOns == null) {
            this.rightJoinOns = new ArrayList<>();
        }
        this.rightJoinOns.add(innerJoinOn);
    }

    public List<String> getTableName() {
        return tableName;
    }

    public List<String> getTableAlias() {
        return tableAlias;
    }

    public Map<String, String> getTableMap() {
        return tableMap;
    }

    public List<String> getInFields() {
        return inFields;
    }

    public List<Object> getInValue1() {
        return inValue1;
    }

    public List<Object> getInValue2() {
        return inValue2;
    }

    public List<String> getLikeFields() {
        return likeFields;
    }

    public List<String> getLikeValues() {
        return likeValues;
    }

    public boolean isUseMultipart() {
        return useMultipart;
    }

    public void setUseMultipart(boolean useMultipart) {
        this.useMultipart = useMultipart;
    }

    public List<String> getEqualsWhereKey() {
        return equalsWhereKey;
    }

    public List<Object> getEqualsWhereValue() {
        return equalsWhereValue;
    }

    public List<String> getGreaterThanWhereKey() {
        return greaterThanWhereKey;
    }

    public List<Object> getGreaterThanWhereValue() {
        return greaterThanWhereValue;
    }

    public List<String> getLessThanWhereKey() {
        return lessThanWhereKey;
    }

    public List<String> getGreaterThanOrEqualToWhereKey() {
        return greaterThanOrEqualToWhereKey;
    }

    public List<Object> getGreaterThanOrEqualToWhereValue() {
        return greaterThanOrEqualToWhereValue;
    }

    public List<String> getLessThanOrEqualToWhereKey() {
        return lessThanOrEqualToWhereKey;
    }

    public List<Object> getLessThanOrEqualToWhereValue() {
        return lessThanOrEqualToWhereValue;
    }

    public List<String> getLeftJoinOns() {
        return leftJoinOns;
    }

    public List<String> getInnerJoinOns() {
        return innerJoinOns;
    }

    public void setInnerJoinOns(List<String> innerJoinOns) {
        this.innerJoinOns = innerJoinOns;
    }

    public List<Object> getLessThanWhereValue() {
        return lessThanWhereValue;
    }

    public List<String> getNotEqualsWhereKey() {
        return notEqualsWhereKey;
    }

    public List<Object> getNotEqualsWhereValue() {
        return notEqualsWhereValue;
    }

    public List<String> getNotInFields() {
        return notInFields;
    }

    public List<Object> getNotInValue1() {
        return notInValue1;
    }

    public List<Object> getNotInValue2() {
        return notInValue2;
    }

    public List<String> getNotLikeFields() {
        return notLikeFields;
    }

    public List<String> getNotLikeValues() {
        return notLikeValues;
    }

    public List<String> getIsNullFields() {
        return isNullFields;
    }

    public void setIsNullFields(List<String> isNullFields) {
        this.isNullFields = isNullFields;
    }

    public List<String> getIsNotNullFields() {
        return isNotNullFields;
    }

    public void setIsNotNullFields(List<String> isNotNullFields) {
        this.isNotNullFields = isNotNullFields;
    }

    public List<String> getInnerTableAlias() {
        return innerTableAlias;
    }

    public void setInnerTableAlias(List<String> innerTableAlias) {
        this.innerTableAlias = innerTableAlias;
    }

    public Map<String, String> getInnerTableMap() {
        return innerTableMap;
    }

    public void setInnerTableMap(Map<String, String> innerTableMap) {
        this.innerTableMap = innerTableMap;
    }

    public List<String> getInnerTableName() {
        return innerTableName;
    }

    public void setInnerTableName(List<String> innerTableName) {
        this.innerTableName = innerTableName;
    }

    public List<String> getRightTableName() {
        return rightTableName;
    }

    public void setRightTableName(List<String> rightTableName) {
        this.rightTableName = rightTableName;
    }

    public Map<String, String> getRightTableMap() {
        return rightTableMap;
    }

    public void setRightTableMap(Map<String, String> rightTableMap) {
        this.rightTableMap = rightTableMap;
    }

    public List<String> getRightTableAlias() {
        return rightTableAlias;
    }

    public void setRightTableAlias(List<String> rightTableAlias) {
        this.rightTableAlias = rightTableAlias;
    }

    public List<String> getRightJoinOns() {
        return rightJoinOns;
    }

    public void setRightJoinOns(List<String> rightJoinOns) {
        this.rightJoinOns = rightJoinOns;
    }

    public List<String> getExists() {
        return exists;
    }

    public void setExists(List<String> exists) {
        this.exists = exists;
    }

    public List<String> getNotExists() {
        return notExists;
    }

    public void setNotExists(List<String> notExists) {
        this.notExists = notExists;
    }
}
