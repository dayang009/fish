package com.fish.flow;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @author dayang
 * @since 2023/8/13
 */
public class CallExternalSystemDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) {
		System.out.println("正常执行，同意请假的逻辑");
	}

}
