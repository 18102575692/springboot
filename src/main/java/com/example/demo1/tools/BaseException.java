package com.example.demo1.tools;

import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 自定义异常
 */
public class BaseException extends Exception {

    // 表单验证异常
    public static final int FORM_VALIDATION_EXCEPTION = 403;
    // 权限验证异常
    public static final int PERMISSION_VERIFICATION_EXCEPTION = 401;
    // 系统异常
    public static final int SYSTEM_EXCEPTION = 500;
    // 序列化UID
    private static final long serialVersionUID = 8243127099991355146L;
    // 错误码
    private int code;

    /**
     * 构造异常
     *
     * @param code 异常状态码
     * @param msg  异常讯息
     */
    public BaseException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    /**
     * 构造异常
     *
     * @param code 异常状态码
     * @param ex   异常来源
     */
    public BaseException(int code, Exception ex) {
        super(ex);
        this.code = code;
    }

    /**
     * @return 异常状态码。
     */
    public int getErrorCode() {
        return code;
    }

}