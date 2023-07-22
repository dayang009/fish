package com.fish.file.util;

import cn.hutool.core.date.DateUtil;
import com.fish.file.mvnentity.MirrorDTO;
import com.fish.file.mvnentity.ServerDTO;
import com.fish.file.mvnentity.SettingsRoot;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

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
		settingsRoot.setPluginGroup(Arrays.asList("aaa", "bbb"));

		ServerDTO s1 = new ServerDTO("123", "zhangSan", "pwd");
		ServerDTO s2 = new ServerDTO("456", "liSi", "9876");
		settingsRoot.setServerDTOList(Arrays.asList(s1, s2));

		MirrorDTO m1 = new MirrorDTO("tsinghua", "central", "清华大学镜像仓库", "https://repo.maven.apache.org/maven2/");
		MirrorDTO m2 = new MirrorDTO("aliyunmaven", "central,jcenter", "阿里云公共仓库",
				"https://maven.aliyun.com/repository/public/");

		settingsRoot.setMirrorDTOList(Arrays.asList(m1, m2));
		settingsRoot.setNowDate(DateUtil.date());
		settingsRoot.setEndTime(LocalDateTime.now());

		String out = ConvertUtil.beanToXml(settingsRoot, "D:\\temp\\", "set.xml");
		log.info("生成的xml文件全路径名是：{}", out);

	}

	@Test
	void demo02Test() throws IOException {
		MyFtpUtil.upload();
	}

}