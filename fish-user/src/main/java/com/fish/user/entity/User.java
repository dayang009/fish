package com.fish.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

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
	@NotBlank(message = "用户名不能为空")
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
	private Integer adminFlag = 0;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;

	/**
	 * 是否逻辑删除
	 */
	private Boolean deleteFlag = Boolean.FALSE;

	public User(String nickName, String userAccount, String userPwd, Integer gender, Integer age) {
		this.nickName = nickName;
		this.userAccount = userAccount;
		this.userPwd = userPwd;
		this.gender = gender;
		this.age = age;
	}

}
