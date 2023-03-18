package com.konb.chat.service.impl;

import com.konb.chat.constant.CommonConstant;
import com.konb.chat.entity.User;
import com.konb.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author konb
 * @version 1.0
 * @date 2023/3/18 17:45
 */
@Service
public class ChatServiceImpl implements ChatService {

    @Value("${chat.file.path}")
    private String chatDirectoryBasePath;

    @Override
    public boolean initChat(User user) {
        File chatDirectory = new File(chatDirectoryBasePath + getChatDirectoryName(user.getIp(), user.getName()));
        boolean result = false;
        if (!chatDirectory.exists()) {
            result = chatDirectory.mkdirs();
        }
        return result;
    }

    private String getChatDirectoryName(String ip, String name) {
        return ip + CommonConstant.UNDERLINE + name;
    }
}
