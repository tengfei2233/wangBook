package com.wang.exception;

import com.wang.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-20-21:12
 * @description
 */

@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler(Exception.class)
    public R<Void> exceptionHandler(Exception e, HttpServletRequest request) {
        return R.fail("系统错误，请稍后重试");
    }


}
