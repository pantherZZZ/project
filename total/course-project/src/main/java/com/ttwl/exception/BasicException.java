package com.ttwl.exception;

/**
 * @Author zhang bao
 * @Date 2022/5/27 10:43
 * @Description： 基础异常
 * @Version 1.0
 */
public class BasicException extends RuntimeException {
    public BasicException() {
    }

    public BasicException(String message) {
        super(message);
    }

    public BasicException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasicException(Throwable cause) {
        super(cause);
    }

    public BasicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
