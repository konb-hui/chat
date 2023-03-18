package com.konb.chat.entity;

import com.konb.chat.constant.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author konb
 * @version 1.0
 * @date 2023/3/18 18:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Result {
    private int code;
    private Object data;
    private String message;

    public static Result success() {
        return new Result(CommonConstant.SUCCESS_CODE, null, CommonConstant.SUCCESS);
    }

    public static Result error() {
        return new Result(CommonConstant.ERROR_CODE, null, CommonConstant.ERROR);
    }

    public Result data(Object data) {
        this.data = data;
        return this;
    }

    public Result message(String message) {
        this.message = message;
        return this;
    }
}
