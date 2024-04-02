package com.fish.user.config.type;

import com.fish.common.core.util.YangUtil;
import com.fish.user.entity.Restaurant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Restaurant.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class RestaurantTypeHandler extends BaseTypeHandler<Restaurant> {

	/**
	 * 插入时设置参数类型
	 * @param ps SQL预编译对象
	 * @param i 需要赋值的索引位置(相当于在JDBC中对占位符的位置进行赋值)
	 * @param parameter
	 * 索引位置i需要赋的值(原本要给这个位置赋的值，在setNonNullParameter方法中主要解决的问题就是将这个自定义类型变成数据库认识的类型)
	 * @param jdbcType jdbc的类型
	 * @throws SQLException
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Restaurant parameter, JdbcType jdbcType)
			throws SQLException {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		ps.setString(i, gson.toJson(parameter));
	}

	/**
	 * Gets the nullable result. 获取时转换回的自定义类型
	 * @param rs the rs
	 * @param columnName Column name, when configuration <code>useColumnLabel</code> is
	 * <code>false</code>
	 * @return the nullable result
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Restaurant getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return YangUtil.getGson().fromJson(rs.getString(columnName), Restaurant.class);
	}

	/**
	 * 根据索引位置获取
	 * @param rs
	 * @param columnIndex
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Restaurant getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return YangUtil.getGson().fromJson(rs.getString(columnIndex), Restaurant.class);
	}

	/**
	 * 根据存储过程获取
	 * @param cs
	 * @param columnIndex
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Restaurant getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return YangUtil.getGson().fromJson(cs.getString(columnIndex), Restaurant.class);
	}

}
