package com.fish.business.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stud implements Serializable {

	private static final long serialVersionUID = 5100898738579897283L;

	private String id;

	private String name;

	private LocalDateTime startTime;

	private LocalDateTime endTime;

}
