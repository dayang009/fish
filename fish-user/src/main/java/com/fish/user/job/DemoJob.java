package com.fish.user.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dayang
 */
@Slf4j
@Component
public class DemoJob {

	private final AtomicInteger counts = new AtomicInteger();

	@Scheduled(fixedRate = 2000)
	public void execute() {
		log.info("[execute][定时第 ({}) 次执行]", counts.incrementAndGet());
	}

}
