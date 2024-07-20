package com.fish.user.pay.strategy.context;

import com.fish.user.pojo.Order;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractPayContext {

	public abstract String execute(Order order);

}
