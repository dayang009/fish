package com.fish.business.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

	private static final long serialVersionUID = 1681838644694455999L;

	private String id;

	private String name;

	private Integer age;

	private String classNumber;

	private String address;

	private int chainessScores;

	private int mathScores;

}
