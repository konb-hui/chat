package com.konb.chat.entity;

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
}
