package com.fish.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.GsonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息实体类
 *
 * @author dayang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user_info", autoResultMap = true)
public class UserInfo implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = -4012301048914576672L;

	/**
	 * 主键
	 */
	@Schema(description = "主键、自增")
	@TableId(type = IdType.AUTO)
	private String id;

	/**
	 * 昵称
	 */
	@Schema(description = "昵称", example = "mark")
	private String nickName;

	/**
	 * 用户名
	 */
	@Schema(description = "用户名")
	private String userAccount;

	/**
	 * 密码
	 */
	@Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank(message = "密码不能为空")
	@Size(min = 6, max = 20, message = "密码位数必须在{min}和{max}之间")
	private String userPassword;

	/**
	 * 头像链接
	 */
	@Schema(description = "头像链接")
	private String avatarUrl;

	/**
	 * 性别：0---女，1---男
	 */
	@Schema(description = "性别：0-女，1-男", example = "1")
	private Integer gender;

	/**
	 * 年龄
	 */
	@Schema(description = "年龄", example = "18")
	@Min(value = 0, message = "年龄必须是非负")
	@Max(value = 256, message = "年龄最大不能超过{value}")
	private Integer age;

	/**
	 * 手机号
	 */
	@Schema(description = "手机号", requiredMode = Schema.RequiredMode.REQUIRED, example = "18866668888")
	@Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号格式有误")
	private String phone;

	/**
	 * 邮箱
	 */
	@Schema(description = "邮箱", requiredMode = Schema.RequiredMode.REQUIRED, example = "demo@163.com")
	@Email
	private String email;

	/**
	 * 当前用户角色
	 */
	@TableField(typeHandler = GsonTypeHandler.class)
	private Object roles;

	/**
	 * 有哪些权限
	 */
	@TableField(typeHandler = GsonTypeHandler.class)
	private Object permissions;

	/**
	 * 用户状态；0-正常
	 */
	private Integer userStatus = 0;

	/**
	 * 星球编号
	 */
	private String planetCode;

	/**
	 * 标签，Json 格式
	 */
	@TableField(typeHandler = GsonTypeHandler.class)
	private Object tags;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
	private Date createTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
	private Date updateTime;

	/**
	 * 是否逻辑删除
	 */
	@Schema(hidden = true)
	private Boolean deleteFlag = Boolean.FALSE;

	public UserInfo(String userAccount) {
		this.userAccount = userAccount;
	}

	public UserInfo(String userAccount, String userPassword) {
		this.userAccount = userAccount;
		this.userPassword = userPassword;
	}

	public UserInfo(String nickName, String userAccount, String userPassword, Integer gender, Integer age) {
		this.nickName = nickName;
		this.userAccount = userAccount;
		this.userPassword = userPassword;
		this.gender = gender;
		this.age = age;
	}

}
