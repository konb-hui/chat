package com.konb.chat.handler;

import com.alibaba.fastjson.JSON;
import com.konb.chat.constant.CommonConstant;
import com.konb.chat.entity.ChatMessage;
import com.konb.chat.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author konb
 * @version 1.0
 * @date 2023/3/19 0:30
 */
@Component
public class MyHandler extends TextWebSocketHandler {

    @Value("${chat.name}")
    private String chatName;

    private final Map<String, WebSocketSession> sessions = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        if (session.getUri() == null) {
            return;
        }
        UriComponents uri = UriComponentsBuilder.fromUri(session.getUri()).build();
        String name = uri.getQueryParams().getFirst("name");
        if (!StringUtils.hasText(name)) {
            return;
        }
        User user = new User();
        user.setIp(Objects.requireNonNull(session.getRemoteAddress()).getAddress().getHostAddress());
        user.setName(name);
        System.out.println(user);
        session.getAttributes().put("user", JSON.toJSONString(user));
        sessions.put(user.getUserKey(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println(message.getPayload());
        ChatMessage chatMessage = JSON.parseObject(message.getPayload(), ChatMessage.class);
        if (CommonConstant.GPT_MODE == chatMessage.getMode()) {
            User user = getUserFromSession(session);
            if (user != null) {
                chatMessage.setToUser(user);
                sessions.get(chatName).sendMessage(new TextMessage(JSON.toJSONBytes(chatMessage)));
            }
        }
        if (CommonConstant.GPT_ANSWER_MESSAGE == chatMessage.getType()) {
            User toUser = chatMessage.getToUser();
            if (toUser != null) {
                WebSocketSession webSocketSession = sessions.get(toUser.getUserKey());
                if (webSocketSession != null) {
                    webSocketSession.sendMessage(message);
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        User userFromSession = getUserFromSession(session);
        if (userFromSession == null) {
            return;
        }
        sessions.remove(userFromSession.getUserKey());
    }

    private User getUserFromSession(WebSocketSession session) {
        String userStr = (String) session.getAttributes().get("user");
        if (StringUtils.hasText(userStr)) {
            return JSON.parseObject(userStr, User.class);
        }
        return null;
    }
}
