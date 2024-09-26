package com.fish.user.ws;

import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@Slf4j
@ServerEndpoint("/websocket/{userId}")
public class WebSocketServer {

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	private static final CopyOnWriteArraySet<WebSocketServer> webSockets = new CopyOnWriteArraySet<>();

	// 用来存在线连接数
	private static final Map<String, Session> sessionPool = new HashMap<>();

	/**
	 * 链接成功调用的方法
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam(value = "userId") String userId) {
		try {
			this.session = session;
			webSockets.add(this);
			sessionPool.put(userId, session);
			log.info("websocket消息: 有新的连接，总数为: {}", webSockets.size());
		}
		catch (Exception e) {
		}
	}

	/**
	 * 收到客户端消息后调用的方法
	 */
	@OnMessage
	public void onMessage(String message) {
		log.info("websocket消息: 收到客户端消息: {}", message);
	}

	/**
	 * 此为单点消息
	 */
	public void sendOneMessage(String userId, String message) {
		Session session = sessionPool.get(userId);
		if (session != null && session.isOpen()) {
			try {
				log.info("websocket消: 单点消息: {}", message);
				session.getAsyncRemote().sendText(message);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
