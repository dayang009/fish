package com.fish.file.monitor;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

/**
 * 自定义文件监控类，通过指定目录创建一个观察者 FileAlterationObserver
 *
 * @author dayang
 */
public class FileMonitor {

	private final FileAlterationMonitor monitor;

	public FileMonitor() {
		monitor = new FileAlterationMonitor(1000);
	}

	public FileMonitor(long interval) {
		monitor = new FileAlterationMonitor(interval);
	}

	/**
	 * 给文件添加监听
	 * @param filePath 文件路径
	 * @param listener 文件监听器
	 */
	public void monitor(String filePath, FileAlterationListener listener) {

		// 创建文件过滤器
		// 前缀过滤器
		IOFileFilter prefixFileFilter = FileFilterUtils.prefixFileFilter("a");
		// 后缀过滤器
		IOFileFilter suffixFileFilter = FileFilterUtils.suffixFileFilter(".xml");
		// 两者都满足
		IOFileFilter filter = FileFilterUtils.and(prefixFileFilter, suffixFileFilter);

		FileAlterationObserver observer = new FileAlterationObserver(new File(filePath), filter);
		monitor.addObserver(observer);
		observer.addListener(listener);
	}

	public void start() throws Exception {
		monitor.start();
	}

	public void stop() throws Exception {
		monitor.stop();
	}

}
