package com.fish.user.pojo;

import com.fish.user.ordermanagement.state.OrderState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	private String orderId;

	private String productId;

	/**
	 * 订单状态
	 */
	private OrderState orderState;

	/**
	 * 商品价格
	 */
	private Float price;

	/**
	 * 当前用户唯一Id
	 */
	private String userId;

}
