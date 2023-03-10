package com.sjz.cloudcourse.common;

import java.util.HashMap;

public class R {
    public static final int STATE_SUCCESS = 0;
    public static final int STATE_ERROR = 500;
    public static final int STATE_FORBIDDEN = 100;
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code;
    private HashMap<String, Object> data;

    public R() {
        this.code = STATE_SUCCESS;
        this.msg = "操作成功";
        this.data = new HashMap<>();
    }

    private R(String msg) {
        this();
        this.msg = msg;
    }

    public R(int code, String msg) {
        this(msg);
        this.code = code;
    }

    public static R error() {
        return new R(STATE_ERROR, "未知异常，请稍后再试");
    }

    public static R error(String msg) {
        return new R(STATE_ERROR,msg);
    }

    public static R ok(String msg) {
        return new R(msg);
    }

    public static R ok() {
        return new R();
    }

    public static R forbidden(String msg) {
        return new R(STATE_FORBIDDEN,msg);
    }

    public R put(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }


}

