package com.konb.chat.service;

import com.konb.chat.entity.User;

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
}
