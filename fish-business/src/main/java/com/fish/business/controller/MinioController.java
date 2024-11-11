package com.fish.business.controller;

import com.fish.business.util.MinioUtil;
import io.minio.MinioClient;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/minio")
public class MinioController {

	@Resource
	private MinioClient minioClient;

	@Resource
	private MinioUtil minioUtil;

	@PostMapping("/upload")
	public void upload(@RequestParam(name = "file", required = false) MultipartFile file, HttpServletRequest request) {
		minioUtil.uploadFile(file, "product");
	}

	@DeleteMapping("/delete")
	public void delete(String name) throws Exception {
		// todo
		minioClient.removeObject(null);
	}

}
