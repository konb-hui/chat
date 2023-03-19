package com.konb.chat.handler;

import com.alibaba.fastjson.JSON;
import com.konb.chat.entity.ChatMessage;
import com.konb.chat.entity.User;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author konb
 * @version 1.0
 * @date 2023/3/19 0:30
 */
public class MyHandler extends TextWebSocketHandler {
    private final Map<String, WebSocketSession> sessions = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        if (session.getUri() == null) {
            return;
        }
        UriComponents uri = UriComponentsBuilder.fromUri(session.getUri()).build();
        String userInfoStr = uri.getQueryParams().getFirst("userInfo");
        if (!StringUtils.hasText(userInfoStr)) {
            return;
        }
        try {
            userInfoStr = URLDecoder.decode(userInfoStr, String.valueOf(StandardCharsets.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(userInfoStr);
        User user = new User();
        if (userInfoStr != null) {
            user = JSON.parseObject(userInfoStr, User.class);
            session.getAttributes().put("user", user.getUserKey());
        }
        sessions.put(user.getUserKey(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println(message.getPayload());
        ChatMessage chatMessage = JSON.parseObject(message.getPayload(), ChatMessage.class);
        String toUserKey = chatMessage.getToUser().getUserKey();
        if (sessions.containsKey(toUserKey)) {
            sessions.get(toUserKey).sendMessage(message);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String user = (String) session.getAttributes().get("user");
        sessions.remove(user);
    }

    private User getUserFromSession(WebSocketSession session) {
        return null;
    }
}
