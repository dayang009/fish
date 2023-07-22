package com.fish.file.mvnentity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
public class MirrorDTO implements Serializable {

	private static final long serialVersionUID = -4502289485517611591L;

	private String id;

	@XmlElement(name = "mirrorOf")
	private String mirrorOf;

	private String name;

	private String url;

}
