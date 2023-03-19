package com.konb.chat.entity;

import com.konb.chat.constant.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author konb
 * @version 1.0
 * @date 2023/3/18 18:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String ip;
    private String name;

    public String getUserKey() {
        return this.ip + CommonConstant.UNDERLINE + this.name;
    }
}
