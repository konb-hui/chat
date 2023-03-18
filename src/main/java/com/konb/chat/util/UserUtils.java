package com.konb.chat.util;

import com.konb.chat.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author konb
 * @version 1.0
 * @date 2023/3/18 16:55
 */

public class UserUtils {
    public static void saveUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute(user.getIp(), user);
    }
}
