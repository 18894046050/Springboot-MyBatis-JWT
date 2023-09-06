package com.demo.exception;


import com.demo.wrapper.Status;

/**
 * 全局异常
 *
 * @author WangJiangQi
 * @since 2023-02-14
 */
public class SecurityException extends BaseException {

    public SecurityException(Status status) {
        super(status);
    }

    public SecurityException(Status status, Object data) {
        super(status, data);
    }

    public SecurityException(Integer code, String message) {
        super(code, message);
    }

    public SecurityException(Integer code, String message, Object data) {
        super(code, message, data);
    }

}
