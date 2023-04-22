package com.wang.exception;

/**
 * @Author: tengfei
 * @Create: 2023 04 22 11:05
 * @Description
 **/

public class OrderException extends RuntimeException{

    public OrderException(String message) {
        super(message);
    }
}
