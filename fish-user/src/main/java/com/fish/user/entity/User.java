package com.fish.user.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
	@Schema(description = "主键、自增")
	private String id;

	/**
	 * 昵称
	 */
	@Schema(description = "昵称", example = "mark")
	private String nickName;

	/**
	 * 账户
	 */
	@Schema(description = "账户", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotEmpty(message = "账户不能为空")
	private String userAccount;

	/**
	 * 密码
	 */
	@Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank(message = "密码不能为空")
	@Size(min = 6, max = 20, message = "密码位数必须在{min}和{max}之间")
	private String userPassword;

	/**
	 * 性别：0---女，1---男
	 */
	@Schema(description = "性别：0-女，1-男", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1")
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
	@NotEmpty(message = "邮箱信息不能为空")
	private String email;

	/**
	 * 0-普通用户，1-管理员
	 */
	@Schema(description = "0-普通用户，1-管理员", hidden = true)
	private Integer adminFlag = 0;

	private List<Restaurant> restaurant;

	/**
	 * 创建时间 前端上送 2020年10月10日11时17分31秒
	 */
	@DateTimeFormat(pattern = "yyyy年MM月dd日HH时mm分ss秒")
	private LocalDateTime createTime;

	/**
	 * 更新时间 个性化输出，返回给前端
	 */
	// @JsonFormat(pattern = "yyyy年MM月dd日HH时mm分ss秒")
	private LocalDateTime updateTime;

	/**
	 * 是否逻辑删除
	 */
	@Schema(hidden = true)
	private Boolean deleteFlag = Boolean.FALSE;

	public User(String nickName, String userAccount, String userPassword, Integer gender, Integer age) {
		this.nickName = nickName;
		this.userAccount = userAccount;
		this.userPassword = userPassword;
		this.gender = gender;
		this.age = age;
	}

}
