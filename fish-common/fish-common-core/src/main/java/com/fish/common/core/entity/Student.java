package com.fish.common.core.entity;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -4012301048914576672L;

	/**
	 * 主键
	 */
	private String id;

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
	@Size(min = 6, max = 20, message = "密码位数必须在{min}和{max}之间")
	private String userPwd;

	/**
	 * 性别：0---女，1---男
	 */
	@Schema(example = "1")
	private Integer gender;

	/**
	 * 年龄
	 */
	@Min(value = 0, message = "年龄必须是非负")
	@Max(value = 256, message = "年龄最大不能超过{value}")
	private Integer age;

	/**
	 * 手机号
	 */
	@Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号格式有误")
	private String phone;

	/**
	 * 邮箱
	 */
	@Email
	@NotEmpty(message = "邮箱信息不能为空")
	private String email;

	/**
	 * 0-普通用户，1-管理员
	 */
	private Integer adminFlag = 0;

	public Student(String nickName, String userAccount, String userPwd, Integer gender, Integer age) {
		this.nickName = nickName;
		this.userAccount = userAccount;
		this.userPwd = userPwd;
		this.gender = gender;
		this.age = age;
	}

}
