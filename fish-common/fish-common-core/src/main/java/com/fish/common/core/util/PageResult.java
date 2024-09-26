package com.fish.common.core.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult implements Serializable {

	private Integer page;

	private Integer size;

	private Integer total;

}
