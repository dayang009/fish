package com.fish.user.netty;

import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.channel.nio.NioEventLoopGroup;
import org.junit.jupiter.api.Test;

public class NettyServerTest {

	@Test
	public void test01() {
		new NioEventLoopGroup();
	}

}
