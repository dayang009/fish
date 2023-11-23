package com.fish.business.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.ftp.Ftp;
import cn.hutool.extra.ftp.FtpMode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author dayang
 */
@Slf4j
public class MyFtpUtil {

	public static void upload() {
		FTPClient ftpClient = new FTPClient();
		BufferedInputStream inputStream = null;
		ftpClient.setControlEncoding(StandardCharsets.UTF_8.name());

		try {
			ftpClient.connect("localhost", 21);
			ftpClient.login("root", "root");

			inputStream = new BufferedInputStream(Files.newInputStream(Paths.get("D:\\temp\\set.xml")));
			ftpClient.setBufferSize(2048);
			ftpClient.setControlEncoding(StandardCharsets.UTF_8.name());
			// 设置文件类型
			ftpClient.setFileType(FTPClient.FILE_STRUCTURE);
			// 设置被动模式
			ftpClient.enterLocalPassiveMode();
			ftpClient.storeFile("E:/temp/set.xml", inputStream);
			inputStream.close();
			ftpClient.logout();
		}
		catch (IOException e) {
			throw new RuntimeException("***** FTP Client RUN ERROR *****", e);
		}
		finally {
			IOUtils.closeQuietly(inputStream);
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				}
				catch (IOException e) {
					log.error("Close FTP Connected ERROR");
				}
			}
		}

	}

	public static boolean htUploda(String upLoadFilePath) throws IOException {

		Ftp ftp = new Ftp("192.168.188.7", 21, "ftpuser", "123456", StandardCharsets.UTF_8);
		// 设置为被动模式
		ftp.setMode(FtpMode.Passive);
		// 进入远程目录

		// 上传本地文件
		ftp.uploadFileOrDirectory(null, FileUtil.file(upLoadFilePath));
		// 下载远程文件
		// ftp.download("/opt/upload", "test.jpg", FileUtil.file("e:/test2.jpg"));

		// 关闭连接
		ftp.close();
		return true;

	}

}
