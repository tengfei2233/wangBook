package com.wang.exception;

import com.alibaba.fastjson2.JSON;
import com.wang.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-20-21:12
 * @description
 */

@Slf4j
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(BookException.class)
    public R<Void> bookExceptionHandler(BookException e, HttpServletRequest request) {
        log.error("书籍类异常", JSON.toJSONString(e));
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(UserException.class)
    public R<Void> userExceptionHandler(UserException e, HttpServletRequest request) {
        log.error("用户类异常", JSON.toJSONString(e));
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(OrderException.class)
    public R<Void> orderExceptionHandler(OrderException e, HttpServletRequest request) {
        log.error("订单类异常", JSON.toJSONString(e));
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(OtherException.class)
    public R<Void> otherExceptionHandler(OtherException e, HttpServletRequest request) {
        log.error("其它异常", JSON.toJSONString(e));
        return R.fail(e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public R<Void> exceptionHandler(Exception e, HttpServletRequest request) {
        log.error("未知异常", JSON.toJSONString(e));
        return R.fail("系统错误，请稍后重试");
    }


}
