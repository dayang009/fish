package com.fish.user.config.type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义<code>Object.class</code>类型处理器
 *
 * @author dayang
 */
public class CustomObjTypeHandler extends BaseTypeHandler<Object> {

	private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

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
	public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType)
			throws SQLException {
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
	public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return gson.fromJson(rs.getString(columnName), Object.class);
	}

	/**
	 * 根据索引位置获取
	 * @param rs
	 * @param columnIndex
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return gson.fromJson(rs.getString(columnIndex), Object.class);
	}

	/**
	 * 根据存储过程获取
	 * @param cs
	 * @param columnIndex
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return gson.fromJson(cs.getString(columnIndex), Object.class);
	}

}
