package com.chain.cold.common.exception;

import lombok.Data;

/**
 * @author AprilGouzi
 * version 1.0
 * 自定义异常
 */
@Data
public class MyException extends RuntimeException{

    private static final long serialVersionUID = 1442447030703558869L;

    private String msg;
    private int code = 500;

    public MyException(String msg){
        super(msg);
        this.msg = msg;
    }
    public MyException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public MyException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public MyException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
