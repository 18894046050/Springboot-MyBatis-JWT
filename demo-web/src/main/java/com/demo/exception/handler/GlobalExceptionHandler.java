package com.demo.exception.handler;


import com.demo.utils.JsonUtil;
import com.demo.wrapper.ApiResponse;
import com.demo.wrapper.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局统一异常处理
 *
 * @author WangJiangQi
 * @since 2023-02-14
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResponse handlerException(Exception e) {
        if (e instanceof HttpRequestMethodNotSupportedException) {
            log.error(
                    "[Global exception interception] HttpRequestMethodNotSupportedException, current request method: {}, supported request method: {}",
                    ((HttpRequestMethodNotSupportedException) e).getMethod(), JsonUtil.toJsonString(((HttpRequestMethodNotSupportedException) e)
                            .getSupportedHttpMethods()));
            return ApiResponse.ofStatus(Status.HTTP_BAD_METHOD);
        } else if (e instanceof BadCredentialsException) {
            log.error("[Global exception interception] BadCredentialsException, message: {}",
                    e.getMessage());
            return ApiResponse.ofStatus(Status.USERNAME_PASSWORD_ERROR);
        } else {
            e.printStackTrace();
            log.error(String.valueOf(e));
            return ApiResponse.ofStatus(Status.ERROR, e.getMessage());
        }
    }

}
