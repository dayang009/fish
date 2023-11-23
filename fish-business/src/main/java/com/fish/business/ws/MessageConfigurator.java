package com.fish.business.ws;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * 配置服务端点，比如配置握手信息
 */
@Slf4j
public class MessageConfigurator extends ServerEndpointConfig.Configurator {

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		log.debug("握手请求头信息：" + request.getHeaders());
		log.debug("握手响应头信息：" + response.getHeaders());
		super.modifyHandshake(sec, request, response);
	}

}
