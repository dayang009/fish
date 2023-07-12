package com.fish.register.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dayang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class MyUser implements Serializable {

	private static final long serialVersionUID = 3816031481186055443L;

	private Integer id;

	private String name;

	private Integer age;

	private Date createTime;

}
