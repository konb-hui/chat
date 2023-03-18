package com.konb.chat.controller;

import com.konb.chat.entity.Result;
import com.konb.chat.entity.User;
import com.konb.chat.service.UserService;
import com.konb.chat.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author konb
 * @version 1.0
 * @date 2023/3/18 18:41
 */
@RestController("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public Result register(HttpServletRequest request, @RequestBody User user) {
        String ip = request.getRemoteAddr();
        user.setIp(ip);
        boolean addUser = userService.addUser(user);
        if (addUser) {
            UserUtils.saveUser(request, user);
            return Result.success();
        }
        return Result.error();
    }

    @GetMapping("check/user")
    public Result checkUser(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        boolean userIsExist = userService.checkUserIsExist(ip);
        if (userIsExist) {
            HttpSession session = request.getSession();
            return Result.success().data(session.getAttribute(ip));
        }
        return Result.error();
    }

}
