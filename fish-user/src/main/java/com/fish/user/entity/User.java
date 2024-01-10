package com.fish.user.entity;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
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
	@NotEmpty(message = "用户名不能为空")
	private String userAccount;

	/**
	 * 密码
	 */
	@Hidden
	@NotBlank(message = "密码不能为空")
	@Size(min = 6, max = 20, message = "密码最少 6 位，最大 20 位")
	private String userPwd;

	/**
	 * 性别：0---女，1---男
	 */
	@Schema(example = "1")
	private Integer gender;

	/**
	 * 年龄
	 */
	@Min(value = 1, message = "年龄必须是非负")
	@Max(value = 256)
	private Integer age;

	/**
	 * 手机号
	 */
	@Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号格式有误")
	private String phone;

	/**
	 * 邮箱
	 */
	@Email(message = "邮箱格式有误")
	@NotEmpty(message = "邮箱为必填项")
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
