package com.fish.business.ws;

import com.fish.business.entity.WSBaseReq;
import com.fish.business.entity.WSReqTypeEnum;
import com.fish.business.service.WebSocketService;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

@Slf4j
@ChannelHandler.Sharable
public class NettyWebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

	@Resource
	private WebSocketService webSocketService;

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
			log.info("握手完成");
		}
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		String text = msg.text();
		Gson gson = new Gson();
		WSBaseReq wsBaseReq = gson.fromJson(text, WSBaseReq.class);
		WSReqTypeEnum wsReqTypeEnum = WSReqTypeEnum.of(wsBaseReq.getType());
		switch (wsReqTypeEnum) {
			case LOGIN:
				this.webSocketService.handleLoginReq(ctx.channel());
				log.info("请求二维码 = " + msg.text());
				break;
			case HEARTBEAT:
				break;
			default:
				log.info("未知类型");
		}
	}

}
