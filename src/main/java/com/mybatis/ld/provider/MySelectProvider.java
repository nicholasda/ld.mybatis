package com.mybatis.ld.provider;

import com.mybatis.ld.util.MyExampleSqlHelp;
import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;

public class MySelectProvider extends MapperTemplate {

    public MySelectProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String selectPage(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        //修改返回值类型为实体类型
        setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder("SELECT ");
        sql.append(MyExampleSqlHelp.getColumn());
        sql.append(MyExampleSqlHelp.isUseAlias());
        sql.append(MyExampleSqlHelp.isUseInnerJoin());
        sql.append(MyExampleSqlHelp.isUseLeftJoin());
        sql.append(MyExampleSqlHelp.isUseRightJoin());
        sql.append(MyExampleSqlHelp.useStartWhereLabel());
        sql.append(MyExampleSqlHelp.useWhereAndEqualsWhere());
        sql.append(MyExampleSqlHelp.useGreaterThan());
        sql.append(MyExampleSqlHelp.useGreaterThanOrEqualTo());
        sql.append(MyExampleSqlHelp.useLessThan());
        sql.append(MyExampleSqlHelp.useLessThanOrEqualTo());
        sql.append(MyExampleSqlHelp.useNotEquals());
        sql.append(MyExampleSqlHelp.useIn());
        sql.append(MyExampleSqlHelp.useNotIn());
        sql.append(MyExampleSqlHelp.useLike());
        sql.append(MyExampleSqlHelp.useNotLike());
        sql.append(MyExampleSqlHelp.useIsNull());
        sql.append(MyExampleSqlHelp.useIsNotNull());
        sql.append(MyExampleSqlHelp.useExists());
        sql.append(MyExampleSqlHelp.useNotExists());
        sql.append(MyExampleSqlHelp.useCondition());
        sql.append(MyExampleSqlHelp.useEndWhereLabel());
        sql.append(MyExampleSqlHelp.useGroup());
        sql.append(MyExampleSqlHelp.useOrder());
        return sql.toString();
    }

    public String selectEntity(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        //修改返回值类型为实体类型
        setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder("SELECT ");
        sql.append(MyExampleSqlHelp.getColumn());
        sql.append(MyExampleSqlHelp.isUseAlias());
        sql.append(MyExampleSqlHelp.isUseInnerJoin());
        sql.append(MyExampleSqlHelp.isUseLeftJoin());
        sql.append(MyExampleSqlHelp.isUseRightJoin());
        sql.append(MyExampleSqlHelp.useStartWhereLabel());
        sql.append(MyExampleSqlHelp.useWhereAndEqualsWhere());
        sql.append(MyExampleSqlHelp.useGreaterThan());
        sql.append(MyExampleSqlHelp.useGreaterThanOrEqualTo());
        sql.append(MyExampleSqlHelp.useLessThan());
        sql.append(MyExampleSqlHelp.useLessThanOrEqualTo());
        sql.append(MyExampleSqlHelp.useNotEquals());
        sql.append(MyExampleSqlHelp.useIn());
        sql.append(MyExampleSqlHelp.useNotIn());
        sql.append(MyExampleSqlHelp.useLike());
        sql.append(MyExampleSqlHelp.useNotLike());
        sql.append(MyExampleSqlHelp.useIsNull());
        sql.append(MyExampleSqlHelp.useIsNotNull());
        sql.append(MyExampleSqlHelp.useExists());
        sql.append(MyExampleSqlHelp.useNotExists());
        sql.append(MyExampleSqlHelp.useCondition());
        sql.append(MyExampleSqlHelp.useEndWhereLabel());
        sql.append(MyExampleSqlHelp.useGroup());
        sql.append(MyExampleSqlHelp.useOrder());
        return sql.toString();
    }
}
