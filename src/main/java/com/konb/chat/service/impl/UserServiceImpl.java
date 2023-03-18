package com.konb.chat.service.impl;

import com.konb.chat.constant.CommonConstant;
import com.konb.chat.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author konb
 * @version 1.0
 * @date 2023/3/18 16:54
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private Map<String, String> users;

    @Value("${user.file.path}")
    private String userPath;

    @Value("${user.num.limit}")
    private int userNumLimit;

    @PostConstruct
    private void init() {
        users = new HashMap<>(userNumLimit);
    }

    @PostConstruct
    public void initUser() {
        File file = getUserFile();
        if (file == null) {
            return;
        }
        try (FileReader fileReader = new FileReader(file); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while (StringUtils.hasText((line = bufferedReader.readLine()))) {
                String[] split = line.split(CommonConstant.USER_SEPARATE);
                users.put(split[0], split[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addUser(String ip, String name) {
        if (users.size() >= userNumLimit) {
            return false;
        }
        File userFile = getUserFile();
        if (userFile == null) {
            return false;
        }
        try (FileWriter fileWriter = new FileWriter(userFile, true); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            if (users.size() > 0) {
                bufferedWriter.newLine();
                bufferedWriter.append(ip).append(CommonConstant.USER_SEPARATE).append(name);
                bufferedWriter.flush();
                users.put(ip, name);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private File getUserFile() {
        File file = new File(userPath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.error("user file create failed, error: ", e);
                return null;
            }
        }
        return file;
    }
}
