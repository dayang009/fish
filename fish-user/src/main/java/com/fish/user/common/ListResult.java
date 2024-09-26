package com.fish.user.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 前端分页模型返回结构体
 *
 * @param <T> 泛型数据
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListResult<T> extends SimplePage implements Serializable {

	private static final long serialVersionUID = -1117910208005991025L;

	private List<T> data;

}
