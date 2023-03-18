package com.konb.chat.service.impl;

import com.konb.chat.constant.CommonConstant;
import com.konb.chat.entity.User;
import com.konb.chat.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * @author konb
 * @version 1.0
 * @date 2023/3/18 17:45
 */
@Service
public class ChatServiceImpl implements ChatService {
    private Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);

    @Value("${chat.file.path}")
    private String chatDirectoryBasePath;

    @Value("${chat.num.limit}")
    private int chatNumLimit;

    @Override
    public boolean initChat(User user) {
        File chatDirectory = new File(chatDirectoryBasePath + getChatDirectoryName(user));
        boolean result = false;
        if (!chatDirectory.exists()) {
            result = chatDirectory.mkdirs();
        }
        return result;
    }

    @Override
    public boolean addChat(User user, User toUser, String name) {
        File chatDirectory = new File(getChatDirectoryName(user) + File.separator + getChatDirectoryName(toUser));
        if (!chatDirectory.exists()) {
            boolean mkdirs = chatDirectory.mkdirs();
            if (!mkdirs) {
                logger.error("user chat create failed, user: {}", user.toString());
                return false;
            }
        } else {
            File[] files = chatDirectory.listFiles();
            if (files != null && files.length >= chatNumLimit) {
                return false;
            }
        }
        File chatFile = new File(chatDirectory.getAbsoluteFile() + File.separator + name);
        if (!chatFile.exists()) {
            try {
                boolean newFile = chatFile.createNewFile();
                if (!newFile) {
                    logger.error("user chat create failed, user: {}", user.toString());
                    return false;
                }
            } catch (IOException e) {
                logger.error("user chat create failed, user: {}, error: {}", user.toString(), e);
                e.printStackTrace();
            }
        }
        return true;
    }

    private String getChatDirectoryName(User user) {
        return user.getIp() + CommonConstant.UNDERLINE + user.getName();
    }
}
