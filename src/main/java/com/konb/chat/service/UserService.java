package com.konb.chat.service;

import com.konb.chat.entity.User;

/**
 * @author konb
 * @version 1.0
 * @date 2023/3/18 16:54
 */

public interface UserService {

    /**
     * 添加用户
     *
     * @param user 用户
     * @return 添加结果
     */
    boolean addUser(User user);

    /**
     * 判断当前ip是否存在账号
     *
     * @param ip ip
     * @return 查询结果
     */
    boolean checkUserIsExist(String ip);
}
