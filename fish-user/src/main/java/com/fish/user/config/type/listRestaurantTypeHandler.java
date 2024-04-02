package com.fish.user.config.type;

import com.fish.user.entity.Restaurant;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class listRestaurantTypeHandler extends BaseTypeHandler<List<Restaurant>> {

	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	private final Type listType = new TypeToken<List<Restaurant>>() {
	}.getType();

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, List<Restaurant> parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, gson.toJson(parameter, listType));
	}

	/**
	 * Gets the nullable result.
	 * @param rs the rs
	 * @param columnName Column name, when configuration <code>useColumnLabel</code> is
	 * <code>false</code>
	 * @return the nullable result
	 * @throws SQLException the SQL exception
	 */
	@Override
	public List<Restaurant> getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return gson.fromJson(rs.getString(columnName), listType);
	}

	@Override
	public List<Restaurant> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return gson.fromJson(rs.getString(columnIndex), listType);
	}

	@Override
	public List<Restaurant> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return gson.fromJson(cs.getString(columnIndex), listType);
	}

}
