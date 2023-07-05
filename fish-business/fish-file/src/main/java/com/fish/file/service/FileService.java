package com.fish.file.service;

import com.fish.file.entity.SatelliteFile;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

/**
 * @author dayang
 */
public interface FileService {

	void handleXmlFile(@RequestParam File file);

	SatelliteFile analysisFile(@RequestParam File file);

}
