package com.fish.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 餐厅表
 *
 * @author dayang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant implements Serializable {

	private static final long serialVersionUID = -3615216903493755316L;

	/**
	 * 餐厅编号
	 */
	private String num;

	/**
	 * 餐厅名称
	 */
	private String name;

	/**
	 * 餐厅位置
	 */
	private String address;

	private Date date;

}
