package com.fish.business.mq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author dayang
 * @since 2023/9/6
 */
public class SyncProducer {

	public static void main(String[] args) throws Exception {
		DefaultMQProducer producer = new DefaultMQProducer("group_test");
		producer.setNamesrvAddr("127.0.0.1:9876");
		producer.setSendLatencyFaultEnable(true);

		producer.start();
		for (int i = 0; i < 10; i++) {
			Message msg = new Message("TopicTest", "TagA",
					("Hello RocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
			SendResult sendResult = producer.send(msg);
			System.out.printf("%s%n", sendResult);
		}
		producer.shutdown();
	}

}
