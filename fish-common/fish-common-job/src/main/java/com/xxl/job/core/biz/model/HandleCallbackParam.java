package com.xxl.job.core.biz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xuxueli
 * @since 2017/3/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HandleCallbackParam implements Serializable {

	private static final long serialVersionUID = 7017761588516096351L;

	private Long logId;

	private Long logDateTim;

	private Integer handleCode;

	private String handleMsg;

}
