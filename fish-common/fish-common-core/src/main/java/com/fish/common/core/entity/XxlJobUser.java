package com.fish.common.core.entity;

import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * @author xuxueli
 * @since 2019-05-04 16:43:12
 */
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
	private Integer role = 0;

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

	public XxlJobUser() {
	}

	public XxlJobUser(Integer id, String username, String password, Integer role, String permission) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.permission = permission;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "XxlJobUser{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\''
				+ ", role=" + role + ", permission='" + permission + '\'' + '}';
	}

}
