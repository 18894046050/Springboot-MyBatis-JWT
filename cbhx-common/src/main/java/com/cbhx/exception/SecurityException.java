package com.cbhx.exception;

import com.cbhx.wrapper.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 全局异常
 *
 * @author WangJiangQi
 * @since 2023-02-06
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
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
