package com.hand.prod.common.exception;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-06-27 17:06
 */
public class MyException extends RuntimeException{

    private static final long serialVersionUID = -1L;
    protected String code;
    protected String msg;


    public MyException(String code) {
        this(code, (String)null, (Throwable)null);
    }

    public MyException(String message, Throwable cause) {
        this((String)null, message, cause);
    }

    public MyException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.msg = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
