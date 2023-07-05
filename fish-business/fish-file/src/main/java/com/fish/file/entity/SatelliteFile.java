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
public class SatelliteFile implements Serializable {

	private static final long serialVersionUID = 2424052587998076915L;

	private FileHead fileHead;

	private FileBody fileBody;

}
