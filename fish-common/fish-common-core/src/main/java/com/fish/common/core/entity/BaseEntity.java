package com.fish.common.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库建表规范必备字段，封装到该基类中，后续的实体类均继承该类
 *
 * @author dayang
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 5511670614698789821L;

	protected Date createTime;

	protected String createBy;

	protected Date updateTime;

	protected String updateBy;

	protected String remark;

	/**
	 * 是否逻辑删除
	 */
	protected Boolean deleteFlag;

	public BaseEntity() {
	}

	public BaseEntity(Date createTime, String createBy, Date updateTime, String updateBy, String remark,
			Boolean deleteFlag) {
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateTime = updateTime;
		this.updateBy = updateBy;
		this.remark = remark;
		this.deleteFlag = deleteFlag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "BaseEntity{" + "createTime=" + createTime + ", createBy='" + createBy + '\'' + ", updateTime="
				+ updateTime + ", updateBy='" + updateBy + '\'' + ", remark='" + remark + '\'' + ", deleteFlag="
				+ deleteFlag + '}';
	}

}
