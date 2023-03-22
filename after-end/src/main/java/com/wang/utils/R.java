package com.wang.utils;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-20-20:15
 * @description
 */

@Data
@ApiModel("返回响应体")
public class R<T> {

    private int code;

    private String msg;

    private T data;

    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public R(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static R<Void> fail() {
        return new R(500, "请求失败");
    }

    public static R<Void> fail(String msg) {
        return new R(500, msg);
    }

    public static R<Void> fail(String msg, int code) {
        return new R(code, msg);
    }

    public static <T> R<T> fail(String msg, T data) {
        return new R(500, msg, data);
    }

    public static R<Void> ok() {
        return new R(200, "请求成功");
    }

    public static R<Void> ok(String msg) {
        return new R(200, msg);
    }

    public static <T> R<T> ok(String msg, T data) {
        return new R(200, msg, data);
    }

}
