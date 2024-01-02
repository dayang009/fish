package com.fish.business.service;

import cn.hutool.core.date.DateUtil;
import org.quartz.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CronExpressionGenerator implements Job {

	/**
	 * 生成Cron表达式
	 * @param executionTime 执行时间点，即将此时间点转换为Cron表达式
	 * @return 生成的Cron表达式
	 */
	public static String generateCronExpression(Date executionTime) throws ParseException {
		// 将执行时间点转换为Cron表达式
		SimpleDateFormat dateFormat = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
		String dateString = dateFormat.format(executionTime);

		CronExpression cronExpression = new CronExpression(dateString);
		return cronExpression.getCronExpression();
	}

	public static void main(String[] args) {

		try {
			// 生成Cron表达式，假设执行时间点为2023年1月1日 12:30:00
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date executionTime = dateFormat.parse("2023-01-01 12:30:00");

			String cronExpression = generateCronExpression(DateUtil.offsetSecond(DateUtil.date(), 50));
			System.out.println("生成的Cron表达式：" + cronExpression);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("模拟接口调用" + DateUtil.now());
	}

}
