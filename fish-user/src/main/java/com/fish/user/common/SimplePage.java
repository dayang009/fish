package com.fish.user.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimplePage implements Serializable {

	private static final long serialVersionUID = -8635586935883231718L;

	/**
	 * 当前页
	 */
	protected long current;

	/**
	 * 每页显示条数，默认 10
	 */
	protected long size = 10;

	/**
	 * 查询列表总记录数
	 */
	protected long total;

}
