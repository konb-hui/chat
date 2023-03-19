package com.konb.chat.entity;

import lombok.Data;

/**
 * @author konb
 * @version 1.0
 * @date 2023/3/19 1:32
 */
@Data
public class ChatMessage {
    private String message;
    private User toUser;
    private int type;
    private int mode;
    private String id;
}
