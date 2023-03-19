package com.konb.chat.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author konb
 * @version 1.0
 * @date 2023/3/19 1:24
 */
public class Base64Utils {
    public static String encode(String plainText) {
        byte[] encodedBytes = Base64.getEncoder().encode(plainText.getBytes(StandardCharsets.UTF_8));
        return new String(encodedBytes, StandardCharsets.UTF_8);
    }

    public static String decode(String base64String) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64String.getBytes(StandardCharsets.UTF_8));
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}