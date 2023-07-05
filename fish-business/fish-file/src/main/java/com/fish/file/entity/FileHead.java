package com.fish.file.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author dayang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileHead implements Serializable {

	private static final long serialVersionUID = -2761301720208527888L;

	private String name;

	private Integer age;

}
