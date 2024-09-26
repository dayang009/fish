package com.fish.business.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.protocol.heartbeat.MessageModel;

import java.nio.charset.StandardCharsets;

/**
 * @author dayang
 * @since 2023/9/6
 */
public class BalanceComuser {

	public static void main(String[] args) throws MQClientException {
		DefaultMQPushConsumer groupConsumer = new DefaultMQPushConsumer("group_consumer");
		groupConsumer.setNamesrvAddr("127.0.0.1:9876");
		groupConsumer.subscribe("TopicTest", "*");
		groupConsumer.setMessageModel(MessageModel.CLUSTERING);

		groupConsumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
			try {
				for (MessageExt msg : msgs) {
					String topic = msg.getTopic();
					String msgBody = new String(msg.getBody(), StandardCharsets.UTF_8);
					String tags = msg.getTags();
					System.out.println("收到消息：" + "topic: " + topic);
					System.out.println("tags: " + tags);
					System.out.println("msg: " + msgBody);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				return ConsumeConcurrentlyStatus.RECONSUME_LATER;
			}
			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		});

		groupConsumer.start();
		// groupConsumer.shutdown();
		System.out.println("消费者启动成功");

	}

}
