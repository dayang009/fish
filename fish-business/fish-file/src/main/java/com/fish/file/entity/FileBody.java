package com.fish.file.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author dayang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileBody implements Serializable {

	private static final long serialVersionUID = 6178593651415031502L;

	private LocalDateTime createTime;

}
