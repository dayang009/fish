package com.fish.file.mvnentity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * XmlType 指定映射XML时的节点顺序，使用该属性时，必须列出JavaBean对象中的所有字段，否则会报错。
 *
 * @author dayang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlType(propOrder = { "localRepository", "interactiveMode", "offline", "pluginGroup", "serverDTOList", "mirrorDTOList",
		"nowDate", "endTime" })
@XmlAccessorType(XmlAccessType.FIELD) // 映射这个类中的所有字段到XML
@XmlRootElement(name = "settings") // 若不指定，默认使用类名小写作为元素名
public class SettingsRoot {

	@XmlElement(name = "localRepository")
	private String localRepository;

	@XmlElement(name = "interactiveMode")
	private Boolean interactiveMode;

	@XmlElement(name = "offline")
	private Boolean offline;

	@XmlElementWrapper(name = "pluginGroups")
	private List<String> pluginGroup;

	@XmlElementWrapper(name = "servers")
	private List<ServerDTO> serverDTOList;

	@XmlElement(name = "mirror")
	private List<MirrorDTO> mirrorDTOList;

	@XmlJavaTypeAdapter(JaxbDateAdapter.class)
	private Date nowDate;

	@XmlJavaTypeAdapter(JaxbNewDateAdapter.class)
	private LocalDateTime endTime;

}
