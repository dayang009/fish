package com.fish.business.mvnentity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * {@link SettingsRoot} xml文件中的一个子节点
 *
 * @author dayang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class ServerDTO implements Serializable {

	private static final long serialVersionUID = -2886522533400914478L;

	@XmlElement(name = "id")
	private String id;

	private String username;

	private String password;

}
