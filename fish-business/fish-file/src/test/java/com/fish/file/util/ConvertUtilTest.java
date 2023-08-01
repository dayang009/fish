package com.fish.file.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.ftp.Ftp;
import cn.hutool.extra.ftp.FtpMode;
import com.fish.file.mvnentity.MirrorDTO;
import com.fish.file.mvnentity.ServerDTO;
import com.fish.file.mvnentity.SettingsRoot;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author dayang
 */
@Slf4j
class ConvertUtilTest {

	@Test
	void beanToXmlTest() throws JAXBException, IOException {

		SettingsRoot settingsRoot = new SettingsRoot();
		settingsRoot.setLocalRepository("");
		settingsRoot.setInteractiveMode(Boolean.TRUE);
		settingsRoot.setOffline(Boolean.FALSE);
		String n1 = "io.spring.javaformat";
		String n2 = "org.springframework.boot";
		settingsRoot.setPluginGroup(Lists.newArrayList(n1, n2));

		ServerDTO s1 = new ServerDTO("123", "zhangSan", "pwd");
		ServerDTO s2 = new ServerDTO("456", "liSi", "9876");
		settingsRoot.setServerDTOList(Lists.newArrayList(s1, s2));

		MirrorDTO m1 = new MirrorDTO("tsinghua", "central", "清华大学镜像仓库", "https://repo.maven.apache.org/maven2/");
		MirrorDTO m2 = new MirrorDTO("aliyunmaven", "central,jcenter", "阿里云公共仓库",
				"https://maven.aliyun.com/repository/public/");

		settingsRoot.setMirrorDTOList(Lists.newArrayList(m1, m2));
		settingsRoot.setNowDate(DateUtil.date());
		settingsRoot.setEndTime(LocalDateTime.now());

		String out = ConvertUtil.beanToXml(settingsRoot, "D:/temp/", "set.xml");
		log.info("生成的xml文件全路径名是 --> {}", out);
		Ftp ftp = new Ftp("192.168.188.7", 21, "ftpuser", "123456", StandardCharsets.UTF_8);
		ftp.setMode(FtpMode.Passive);
		boolean b = ftp.upload("aaa/bv", FileUtil.file(out));
		ftp.close();
		System.out.println(" 文件上传 --> " + b);
		System.out.println(DateUtil.today());
		System.out.println(DateUtil.format(new Date(), "HHmmss"));

	}

}