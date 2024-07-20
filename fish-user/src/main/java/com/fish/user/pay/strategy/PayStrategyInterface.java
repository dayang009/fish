package com.fish.user.pay.strategy;

import com.fish.user.pojo.Order;

public interface PayStrategyInterface {

	/**
	 * 定义公共的支付方法
	 */
	String pay(Order order);

}
