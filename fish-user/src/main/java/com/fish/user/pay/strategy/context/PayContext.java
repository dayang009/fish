package com.fish.user.pay.strategy.context;

import com.fish.user.pay.strategy.PayStrategyInterface;
import com.fish.user.pojo.Order;

public class PayContext extends AbstractPayContext {

	/**
	 * 关联抽象策略类
	 */
	private final PayStrategyInterface payStrategy;

	/**
	 * 设计具体策略
	 */
	public PayContext(PayStrategyInterface payStrategy) {
		this.payStrategy = payStrategy;
	}

	/**
	 * 执行策略
	 */
	@Override
	public String execute(Order order) {
		return this.payStrategy.pay(order);
	}

}
