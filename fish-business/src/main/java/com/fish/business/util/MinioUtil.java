package com.fish.business.util;

import com.fish.business.config.MinioProp;
import com.fish.common.core.exception.FishCloudException;
import com.fish.common.core.util.ResponseEnum;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Component
public class MinioUtil {

	@Resource
	private MinioClient minioClient;

	@Resource
	private MinioProp minioProp;

	@SneakyThrows
	public void createBucket(String bucketName) {

		boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());

		if (!found) {
			log.debug("当前服务器桶名 ==> {} 不存在, 将要执行新建操作", bucketName);
			minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
		}
	}

	public boolean uploadFile(MultipartFile file, String bucketName) {

		if (null == file || !file.getResource().exists() || file.getSize() == 0) {
			throw new FishCloudException(ResponseEnum.USER_NULL_PARAS_ERROR, "上传文件不能为空");
		}

		this.createBucket(bucketName);

		try {
			minioClient.putObject(PutObjectArgs.builder()
				.bucket(bucketName)
				.object(file.getOriginalFilename())
				.stream(file.getInputStream(), file.getSize(), -1)
				.contentType(file.getContentType())
				.build());
		}
		catch (MinioException | IOException e) {
			log.error(e.getMessage());
		}
		catch (NoSuchAlgorithmException | InvalidKeyException e) {
			throw new RuntimeException(e);
		}

		return true;

	}

}
