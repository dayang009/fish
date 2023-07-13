package com.fish.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * t_user
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = -4012301048914576672L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 账户
	 */
	private String userAccount;

	/**
	 * 密码
	 */
	private String userPwd;

	/**
	 * 性别：0---女，1---男
	 */
	private Integer gender;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 手机号
	 */
	private String phone;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 0-普通用户，1-管理员
	 */
	private Integer adminFlag;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;

	/**
	 * 是否逻辑删除
	 */
	private Boolean deleteFlag;

}