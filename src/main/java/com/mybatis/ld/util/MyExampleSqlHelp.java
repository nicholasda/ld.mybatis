package com.mybatis.ld.util;

public class MyExampleSqlHelp {

	/**
	 * 查询指定列和获取表名
	 *
	 * @return 对应sql片段
	 */
	public static String getColumn() {
		return "<foreach collection=\"_parameter.fields\" index=\"index\" item=\"item\" separator=\",\">\r\n" +
				"		${item}\r\n" +
				"	</foreach> from  ${tableName[0]}";
	}


	/**
	 * 查询指定更新列和获取表名
	 *
	 * @return 对应sql片段
	 */
	public static String getUpdateColumn() {
		return "${tableName[0]} SET\r\n" +
				"		<if test=\"@com.mybatis.ld.util.ExampleOGNL@useUpdateField(_parameter)\">\r\n" +
				"			<foreach collection=\"updateFields\" item=\"key\" index=\"index\" separator=\",\">\r\n" +
				"				${key} = #{updateValue[${index}]}\r\n" +
				"			</foreach>\r\n" +
				"		</if>";
	}


	/**
	 * 是否使用别名,有的话则获取第一个别名
	 *
	 * @return 对应sql片段
	 */
	public static String isUseAlias() {
		return "<if test=\"@com.mybatis.ld.util.ExampleOGNL@useAlias(_parameter) \">\r\n" +
				"			${tableAlias[0]}\r\n" +
				"		</if>";
	}

	/**
	 * 是否使用了左连接查询
	 * * @return 对应sql片段
	 */
	public static String isUseLeftJoin() {
		return "<if test=\"@com.mybatis.ld.util.ExampleOGNL@useMultipartAndLeftJoin(_parameter)\">\r\n" +
				"			<foreach collection=\"tableAlias\" item=\"key\" index=\"index\" separator=\"\">\r\n" +
				"				<if test=\"index >= 1\">\r\n" +
				"			        LEFT JOIN\r\n" +
				"					    ${tableName[index]} ${key}\r\n" +
				"			        ON\r\n" +
				"					    ${leftJoinOns[index-1]}\r\n" +
				"				</if>\r\n" +
				"			</foreach>\r\n" +
				"		</if>";
	}

	/**
	 * 是否使用了内连接查询
	 * * @return 对应sql片段
	 */
	public static String isUseInnerJoin() {
		return "<if test=\"@com.mybatis.ld.util.ExampleOGNL@useMultipartAndInnerJoin(_parameter)\">\r\n" +
				"			<foreach collection=\"innerTableAlias\" item=\"key\" index=\"index\" separator=\"\">\r\n" +
				"			        INNER JOIN\r\n" +
				"					    ${innerTableName[index]} ${key}\r\n" +
				"			        ON\r\n" +
				"					    ${innerJoinOns[index]}\r\n" +
				"			</foreach>\r\n" +
				"		</if>";
	}

	/**
	 * 是否使用了右连接查询
	 * * @return 对应sql片段
	 */
	public static String isUseRightJoin() {
		return "<if test=\"@com.mybatis.ld.util.ExampleOGNL@useMultipartAndRightJoin(_parameter)\">\r\n" +
				"			<foreach collection=\"rightTableAlias\" item=\"key\" index=\"index\" separator=\"\">\r\n" +
				"			        RIGHT JOIN\r\n" +
				"					    ${rightTableName[index]} ${key}\r\n" +
				"			        ON\r\n" +
				"					    ${rightJoinOns[index]}\r\n" +
				"			</foreach>\r\n" +
				"		</if>";
	}

	/**
	 * where标签开始
	 *
	 * @return
	 */
	public static String useStartWhereLabel() {
		return "<where>";
	}

	/**
	 * where标签结束
	 *
	 * @return
	 */
	public static String useEndWhereLabel() {
		return "</where>";
	}

	/**
	 * 是否使用了查询和是否使用了等号查询
	 *
	 * @return 对应sql片段
	 */
	public static String useWhereAndEqualsWhere() {
		return "<if test=\"@com.mybatis.ld.util.ExampleOGNL@useWhere(_parameter)\">\r\n" +
				"				<if test=\"@com.mybatis.ld.util.ExampleOGNL@useEqualsWhere(_parameter)\">\r\n" +
				"					<foreach collection=\"equalsWhereKey\" item=\"key\" index=\"index\" separator=\"\"> " +
				"						AND ${key} = #{equalsWhereValue[${index}]} " +
				"					</foreach>\r\n" +
				"				</if>";
	}

	/**
	 * 是否使用了大于查询
	 *
	 * @return 对应sql片段
	 */
	public static String useGreaterThan() {
		return "<if test=\"@com.mybatis.ld.util.ExampleOGNL@useGreaterThanWhere(_parameter)\">\r\n" +
				"					<foreach collection=\"greaterThanWhereKey\" item=\"key\" index=\"index\" separator=\"\"> " +
				"						AND ${key} &gt; #{greaterThanWhereValue[${index}]} " +
				"					</foreach>\r\n" +
				"				</if>";
	}

	/**
	 * 是否使用了大于等于查询
	 *
	 * @return 对应sql片段
	 */
	public static String useGreaterThanOrEqualTo() {
		return "<if test=\"@com.mybatis.ld.util.ExampleOGNL@useGreaterThanOrEqualToWhere(_parameter)\">\r\n" +
				"					<foreach collection=\"greaterThanOrEqualToWhereKey\" item=\"key\" index=\"index\" separator=\"\"> " +
				"						AND ${key} &gt;= #{greaterThanOrEqualToWhereValue[${index}]} " +
				"					</foreach>\r\n" +
				"				</if>";
	}

	/**
	 * 是否使用了小于查询
	 *
	 * @return 对应sql片段
	 */
	public static String useLessThan() {
		return "<if test=\"@com.mybatis.ld.util.ExampleOGNL@useLessThanWhere(_parameter)\">\r\n" +
				"					<foreach collection=\"lessThanWhereKey\" item=\"key\" index=\"index\" separator=\"\"> " +
				"						AND ${key} &lt; #{lessThanWhereValue[${index}]} " +
				"					</foreach>\r\n" +
				"				</if>";
	}

	/**
	 * 是否使用了小于等于查询
	 *
	 * @return 对应sql片段
	 */
	public static String useLessThanOrEqualTo() {
		return "<if test=\"@com.mybatis.ld.util.ExampleOGNL@useLessThanOrEqualToWhere(_parameter)\">\r\n" +
				"					<foreach collection=\"lessThanOrEqualToWhereKey\" item=\"key\" index=\"index\" separator=\"\"> " +
				"						AND ${key} &lt;= #{lessThanOrEqualToWhereValue[${index}]} " +
				"					</foreach>\r\n" +
				"				</if>";
	}

	/**
	 * 是否使用了不等于查询
	 *
	 * @return 对应sql片段
	 */
	public static String useNotEquals() {
		return "<if test =\"@com.mybatis.ld.util.ExampleOGNL@useNotEqualsWhere(_parameter)\">\r\n" +
				"					<foreach collection=\"notEqualsWhereKey\" item=\"key\" index=\"index\" separator=\"\"> " +
				"						AND ${key} != #{notEqualsWhereValue[${index}]}  " +
				"					</foreach>\r\n" +
				"				</if>\r\n" +
				"		</if>";
	}

	/**
	 * 判断是否使用了查询
	 *
	 * @return 对应sql片段
	 */
	public static String notUseWhere() {
		return "<if test=\"@com.mybatis.ld.util.ExampleOGNL@notUseWhere(_parameter)\">\r\n" +
				"			WHERE 1 = 1\r\n" +
				"		</if>";
	}

	/**
	 * 是否使用in查询
	 *
	 * @return
	 */
	public static String useIn() {
		return "<if test=\"@com.mybatis.ld.util.ExampleOGNL@useIn(_parameter)\">\r\n" +
				"			<foreach collection=\"inFields\" item=\"field\" index=\"index\" separator=\"\">\r\n" +
				"				AND ${field} IN\r\n" +
				"				<choose>\r\n" +
				"					<when test=\"index == 0\">\r\n" +
				"						<foreach collection=\"inValue1\" item=\"value1\" index=\"index1\" separator=\",\" open=\"(\" close=\")\">\r\n" +
				"							#{value1}\r\n" +
				"						</foreach>\r\n" +
				"					</when>\r\n" +
				"					<otherwise>\r\n" +
				"						<foreach collection=\"inValue2\" item=\"value2\" index=\"index2\" separator=\",\" open=\"(\" close=\")\">\r\n" +
				"							#{value2}\r\n" +
				"						</foreach>\r\n" +
				"					</otherwise>\r\n" +
				"				</choose>\r\n" +
				"			</foreach>\r\n" +
				"		</if>";
	}

	/**
	 * 是否使用not in查询
	 *
	 * @return
	 */
	public static String useNotIn() {
		return "<if test=\"@com.mybatis.ld.util.ExampleOGNL@useNotIn(_parameter)\">\r\n" +
				"			<foreach collection=\"notInFields\" item=\"field\" index=\"index\" separator=\"\">\r\n" +
				"				AND ${field} NOT IN\r\n" +
				"				<choose>\r\n" +
				"					<when test=\"index == 0\">\r\n" +
				"						<foreach collection=\"notInValue1\" item=\"value1\" index=\"index1\" separator=\",\" open=\"(\" close=\")\">\r\n" +
				"							#{value1}\r\n" +
				"						</foreach>\r\n" +
				"					</when>\r\n" +
				"					<otherwise>\r\n" +
				"						<foreach collection=\"notInValue2\" item=\"value2\" index=\"index2\" separator=\",\" open=\"(\" close=\")\">\r\n" +
				"							#{value2}\r\n" +
				"						</foreach>\r\n" +
				"					</otherwise>\r\n" +
				"				</choose>\r\n" +
				"			</foreach>\r\n" +
				"		</if>";
	}

	/**
	 * 是否使用like查询
	 *
	 * @return
	 */
	public static String useLike() {
		return "<if test=\" @com.mybatis.ld.util.ExampleOGNL@useLike(_parameter)\">\r\n" +
				"			<foreach collection=\"likeFields\" item=\"field\" index=\"index\" separator=\"\">\r\n" +
				"				AND ${field} LIKE #{likeValues[${index}]}\r\n" +
				"			</foreach>\r\n" +
				"		</if>";
	}

	/**
	 * 是否使用not like查询
	 *
	 * @return
	 */
	public static String useNotLike() {
		return "<if test=\" @com.mybatis.ld.util.ExampleOGNL@useNotLike(_parameter)\">\r\n" +
				"			<foreach collection=\"notLikeFields\" item=\"field\" index=\"index\" separator=\"\">\r\n" +
				"				AND ${field} NOT LIKE #{notLikeValues[${index}]}\r\n" +
				"			</foreach>\r\n" +
				"		</if>";
	}

	/**
	 * 是否使用is null查询
	 *
	 * @return
	 */
	public static String useIsNull() {
		return "<if test=\" @com.mybatis.ld.util.ExampleOGNL@useIsNull(_parameter)\">\r\n" +
				"			<foreach collection=\"isNullFields\" item=\"field\" index=\"index\" separator=\"\">\r\n" +
				"				AND ${field} is null\r\n" +
				"			</foreach>\r\n" +
				"		</if>";
	}

	/**
	 * 是否使用is not null查询
	 *
	 * @return
	 */
	public static String useIsNotNull() {
		return "<if test=\" @com.mybatis.ld.util.ExampleOGNL@useIsNotNull(_parameter)\">\r\n" +
				"			<foreach collection=\"isNotNullFields\" item=\"field\" index=\"index\" separator=\"\">\r\n" +
				"				AND ${field} is not null\r\n" +
				"			</foreach>\r\n" +
				"		</if>";
	}

	/**
	 * 是否使用group by
	 *
	 * @return 对应sql片段
	 */
	public static String useGroup() {
		return "<if test=\"@com.mybatis.ld.util.ExampleOGNL@useGroupBy(_parameter) \">\r\n" +
				"			GROUP BY ${groupBy}\r\n" +
				"		</if>";
	}

	/**
	 * 是否使用 order by
	 *
	 * @return 对应sql片段
	 */
	public static String useOrder() {
		return "<if test=\"@com.mybatis.ld.util.ExampleOGNL@useOrderBy(_parameter)\">\r\n" +
				"			ORDER BY \r\n" +
				"			<foreach collection=\"order.keys\" item=\"key\" index=\"index\" separator=\",\">\r\n" +
				"				${key} " +
				"				<choose>\r\n" +
				"					<when test=\"@com.mybatis.ld.util.ExampleOGNL@orderByDesc(key,_parameter)\">\r\n" +
				"						DESC \r\n" +
				"					</when>\r\n" +
				"					<otherwise>\r\n" +
				"						ASC\r\n" +
				"					</otherwise>\r\n" +
				"				</choose>\r\n" +
				"			</foreach>\r\n" +
				"		</if>";
	}
}
