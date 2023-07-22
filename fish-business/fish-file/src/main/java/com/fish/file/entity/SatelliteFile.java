package com.fish.file.entity;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * @author dayang
 */
@XmlType(name = "aaa", propOrder = { "fileHead", "fileBody" })
@XmlAccessorType(XmlAccessType.FIELD) // 映射这个类中的所有字段到XML
@XmlRootElement(name = "InterFaceFile")
public class SatelliteFile implements Serializable {

	private static final long serialVersionUID = 2424052587998076915L;

	@XmlElement(name = "FileHead", defaultValue = "默认文件头", required = true)
	private FileHead fileHead;

	@XmlElement(name = "fileBody")
	private FileBody fileBody;

	public SatelliteFile() {
	}

	public SatelliteFile(FileHead fileHead, FileBody fileBody) {
		this.fileHead = fileHead;
		this.fileBody = fileBody;
	}

	public FileHead getFileHead() {
		return fileHead;
	}

	public void setFileHead(FileHead fileHead) {
		this.fileHead = fileHead;
	}

	public FileBody getFileBody() {
		return fileBody;
	}

	public void setFileBody(FileBody fileBody) {
		this.fileBody = fileBody;
	}

	@Override
	public String toString() {
		return "SatelliteFile{" + "fileHead=" + fileHead + ", fileBody=" + fileBody + '}';
	}

}
