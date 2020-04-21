package com.mybatis.ld.util;

import com.mybatis.ld.constant.ExampleConstants;
import com.mybatis.ld.example.BaseExample;
import com.mybatis.ld.example.select.SelectBaseExample;
import com.mybatis.ld.example.select.multipart.MultipartSelectExample;
import com.mybatis.ld.example.update.UpdateBaseExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class ExampleOGNL {
	static Logger logger = LoggerFactory.getLogger(ExampleOGNL.class);

	/**
	 * 判断是否含有更新字段
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useUpdateField(Object parameter) {
		if (parameter instanceof UpdateBaseExample) {
			UpdateBaseExample ube = (UpdateBaseExample) parameter;
			List<String> updateFields = ube.getUpdateFields();
			return updateFields != null && updateFields.size() != 0;
		}
		return false;
	}

	/**
	 * 判断是否使用多功能查询和左连接查询
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useMultipartAndLeftJoin(Object parameter) {
		if (parameter instanceof MultipartSelectExample) {
			MultipartSelectExample mse = (MultipartSelectExample) parameter;
			return mse.isLeftJoinFlag();
		}
		return false;
	}

	/**
	 * 判断是否使用多功能查询和内连接查询
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useMultipartAndInnerJoin(Object parameter) {
		if (parameter instanceof MultipartSelectExample) {
			MultipartSelectExample mse = (MultipartSelectExample) parameter;
			return mse.isInnerJoinFlag();
		}
		return false;
	}

	/**
	 * 判断是否使用多功能查询和右连接查询
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useMultipartAndRightJoin(Object parameter) {
		if (parameter instanceof MultipartSelectExample) {
			MultipartSelectExample mse = (MultipartSelectExample) parameter;
			return mse.isRightJoinFlag();
		}
		return false;
	}

	/**
	 * 判断是否使用别名
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useAlias(Object parameter) {
		if (parameter != null) {
			BaseExample sbe = (BaseExample) parameter;
			List<String> tableAlias = sbe.getTableAlias();
			return tableAlias != null && tableAlias.size() > 0;
		}
		return false;
	}

	/**
	 * 使用了where查询
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useWhere(Object parameter) {
		if (parameter != null) {
			BaseExample sbe = (BaseExample) parameter;
			return sbe.getEqualsWhereKey() != null || sbe.getGreaterThanWhereKey() != null
					|| sbe.getLessThanWhereKey() != null || sbe.getNotEqualsWhereKey() != null;
		}
		return false;
	}

	/**
	 * 没有使用where查询
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean notUseWhere(Object parameter) {
		if (parameter != null) {
			BaseExample sbe = (BaseExample) parameter;
			return sbe.getEqualsWhereKey() == null && sbe.getGreaterThanWhereKey() == null
					&& sbe.getLessThanWhereKey() == null && sbe.getNotEqualsWhereKey() == null;
		}
		return false;
	}

	/**
	 * 是否使用了大于，小于或者不等于查询
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useGreaterAndLess(Object parameter) {
		if (parameter != null) {
			BaseExample sbe = (BaseExample) parameter;
			List<String> greaterThan = sbe.getGreaterThanWhereKey();
			List<String> lessThanWhere = sbe.getLessThanWhereKey();
			List<String> notEqualsWhere = sbe.getNotEqualsWhereKey();
			return (greaterThan != null && greaterThan.size() > 0) || (lessThanWhere != null && lessThanWhere.size() > 0)
					|| (notEqualsWhere != null && notEqualsWhere.size() > 0);
		}
		return false;
	}

	/**
	 * 是否使用了小于或者不等于查询
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useLessAndNotEquals(Object parameter) {
		if (parameter != null) {
			BaseExample sbe = (BaseExample) parameter;
			List<String> lessThanWhere = sbe.getLessThanWhereKey();
			List<String> notEqualsWhere = sbe.getNotEqualsWhereKey();
			return (lessThanWhere != null && lessThanWhere.size() > 0)
					|| (notEqualsWhere != null && notEqualsWhere.size() > 0);
		}
		return false;
	}

	/**
	 * 是否使用了等号查询
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useEqualsWhere(Object parameter) {
		if (parameter != null) {
			BaseExample sbe = (BaseExample) parameter;
			List<String> equalsWhere = sbe.getEqualsWhereKey();
			return equalsWhere != null && equalsWhere.size() > 0;
		}
		return false;
	}

	/**
	 * 是否使用了exists查询
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useExists(Object parameter) {
		if (parameter != null) {
			BaseExample sbe = (BaseExample) parameter;
			List<String> exists = sbe.getExists();
			return exists != null && exists.size() > 0;
		}
		return false;
	}

	/**
	 * 是否使用了not exists查询
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useNotExists(Object parameter) {
		if (parameter != null) {
			BaseExample sbe = (BaseExample) parameter;
			List<String> notExists = sbe.getNotExists();
			return notExists != null && notExists.size() > 0;
		}
		return false;
	}

	/**
	 * 是否使用了大于查询
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useGreaterThanWhere(Object parameter) {
		if (parameter != null) {
			BaseExample sbe = (BaseExample) parameter;
			List<String> greaterThan = sbe.getGreaterThanWhereKey();
			return (greaterThan != null && greaterThan.size() > 0);
		}
		return false;
	}

	/**
	 * 是否使用了大于等于查询
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useGreaterThanOrEqualToWhere(Object parameter) {
		if (parameter != null) {
			BaseExample sbe = (BaseExample) parameter;
			List<String> greaterThanOrEqualTo = sbe.getGreaterThanOrEqualToWhereKey();
			return (greaterThanOrEqualTo != null && greaterThanOrEqualTo.size() > 0);
		}
		return false;
	}

	/**
	 * 是否使用了小于查询
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useLessThanWhere(Object parameter) {
		if (parameter != null) {
			BaseExample sbe = (BaseExample) parameter;
			List<String> lessThanWhere = sbe.getLessThanWhereKey();
			return lessThanWhere != null && lessThanWhere.size() > 0;
		}
		return false;
	}

	/**
	 * 是否使用了小于等于查询
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useLessThanOrEqualToWhere(Object parameter) {
		if (parameter != null) {
			BaseExample sbe = (BaseExample) parameter;
			List<String> lessThanOrEqualToWhere = sbe.getLessThanOrEqualToWhereKey();
			return lessThanOrEqualToWhere != null && lessThanOrEqualToWhere.size() > 0;
		}
		return false;
	}

	/**
	 * 是否使用了不等于查询
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useNotEqualsWhere(Object parameter) {
		if (parameter != null) {
			BaseExample sbe = (BaseExample) parameter;
			List<String> notEqualsWhere = sbe.getNotEqualsWhereKey();
			return notEqualsWhere != null && notEqualsWhere.size() > 0;
		}
		return false;
	}

	/**
	 * 使用了in查询
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useIn(Object parameter) {
		if (parameter != null) {
			BaseExample sbe = (BaseExample) parameter;
			List<String> inFields = sbe.getInFields();
			return inFields != null && inFields.size() > 0;
		}
		return false;
	}

	/**
	 * 使用了not in查询
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useNotIn(Object parameter) {
		if (parameter != null) {
			BaseExample sbe = (BaseExample) parameter;
			List<String> notInFields = sbe.getNotInFields();
			return notInFields != null && notInFields.size() > 0;
		}
		return false;
	}

	/**
	 * 使用了like查询
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useLike(Object parameter) {
		if (parameter != null) {
			BaseExample sbe = (BaseExample) parameter;
			List<String> likeFields = sbe.getLikeFields();
			return likeFields != null && likeFields.size() > 0;
		}
		return false;
	}

	/**
	 * 使用了not like查询
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useNotLike(Object parameter) {
		if (parameter != null) {
			BaseExample sbe = (BaseExample) parameter;
			List<String> notLikeFields = sbe.getNotLikeFields();
			return notLikeFields != null && notLikeFields.size() > 0;
		}
		return false;
	}

	/**
	 * 使用了is null查询
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useIsNull(Object parameter) {
		if (parameter != null) {
			BaseExample sbe = (BaseExample) parameter;
			List<String> isNullFields = sbe.getIsNullFields();
			return isNullFields != null && isNullFields.size() > 0;
		}
		return false;
	}

	/**
	 * 使用了is not null查询
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useIsNotNull(Object parameter) {
		if (parameter != null) {
			BaseExample sbe = (BaseExample) parameter;
			List<String> isNotNullFields = sbe.getIsNotNullFields();
			return isNotNullFields != null && isNotNullFields.size() > 0;
		}
		return false;
	}

	/**
	 * 是否使用了分组
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useGroupBy(Object parameter) {
		if (parameter != null) {
			SelectBaseExample sbe = (SelectBaseExample) parameter;
			String groupByField = sbe.getGroupBy();
			return groupByField != null && groupByField.length() > 0;
		}
		return false;
	}

	/**
	 * 是否使用了排序
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean useOrderBy(Object parameter) {
		if (parameter != null) {
			SelectBaseExample sbe = (SelectBaseExample) parameter;
			Map<String, Object> orderBy = sbe.getOrder();
			return orderBy != null && orderBy.size() > 0;
		}
		return false;
	}

	/**
	 * 是否是desc排序
	 *
	 * @param parameter 传入对象(BaseExample的子类)
	 * @return boolean
	 */
	public static boolean orderByDesc(String key, Object parameter) {
		if (parameter != null) {
			SelectBaseExample sbe = (SelectBaseExample) parameter;
			Map<String, Object> orderBy = sbe.getOrder();
			return ExampleConstants.ORDER_BY_DESC.equals(orderBy.get(key));
		}
		return false;
	}

}
