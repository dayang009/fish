package com.xxl.job.admin.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * @author xuxueli
 * @since 2019-05-04 16:43:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XxlJobUser implements Serializable {

	private static final long serialVersionUID = -4726988039083508539L;

	private Integer id;

	/**
	 * 账号
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 角色：0-普通用户、1-管理员
	 */
	private Integer role;

	/**
	 * 权限：执行器ID列表，多个逗号分割
	 */
	private String permission;

	/**
	 * plugin
	 * @param jobGroup
	 * @return
	 */
	public boolean validPermission(int jobGroup) {
		if (this.role == 1) {
			return true;
		}
		else {
			if (StringUtils.hasText(this.permission)) {
				for (String permissionItem : this.permission.split(",")) {
					if (String.valueOf(jobGroup).equals(permissionItem)) {
						return true;
					}
				}
			}
			return false;
		}

	}

}
