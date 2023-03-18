package com.konb.chat.service;

/**
 * @author konb
 * @version 1.0
 * @date 2023/3/18 16:54
 */

public interface UserService {

    /**
     * 添加用户
     *
     * @param ip ip
     * @param name 名字
     * @return 添加结果
     */
    boolean addUser(String ip, String name);
}
