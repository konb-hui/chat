package com.konb.chat.service;

import com.konb.chat.entity.ChatMessage;
import com.konb.chat.entity.User;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author konb
 * @version 1.0
 * @date 2023/3/18 16:53
 */

public interface ChatService {
    /**
     * 聊天准备工作
     *
     * @param user 用户
     * @return 初始化结果
     */
    boolean initChat(User user);

    /**
     * 添加新的对话
     *
     * @param user 用户
     * @param toUser 聊天对象
     * @param name 聊天名
     * @return 添加结果
     */
    boolean addChat(User user, User toUser, String name);
}
